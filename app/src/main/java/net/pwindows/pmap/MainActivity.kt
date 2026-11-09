package net.pwindows.pmap

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.style.standard.LightPresetValue
import com.mapbox.maps.extension.compose.style.standard.MapboxStandardStyle
import com.mapbox.maps.extension.compose.style.standard.StandardStyleConfigurationState
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.attribution.Attribution
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.scalebar.ScaleBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import kotlin.math.roundToInt

data class WeatherData(
    val isDay: Boolean,
    val cloudCover: Int,
    val sunrise: String,
    val sunset: String
)

suspend fun fetchWeatherData(lat: Double, lng: Double): WeatherData? = withContext(Dispatchers.IO) {
    try {
        withTimeout(8000L) {
            val url = URL("https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lng&current=is_day,cloud_cover&daily=sunrise,sunset&timezone=GMT")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")

            if (connection.responseCode == 200) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val json = JSONObject(response)
                val current = json.getJSONObject("current")
                val daily = json.getJSONObject("daily")
                WeatherData(
                    current.getInt("is_day") == 1,
                    current.optInt("cloud_cover", 0),
                    daily.getJSONArray("sunrise").getString(0),
                    daily.getJSONArray("sunset").getString(0)
                )
            } else null
        }
    } catch (e: Exception) {
        null
    }
}

class MainActivity : ComponentActivity() {
    lateinit var permissionsManager: PermissionsManager

    @SuppressLint("LocalContextResourcesRead")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context: Context = LocalContext.current
            val prefs = remember { context.getSharedPreferences("pmap_prefs", Context.MODE_PRIVATE) }

            val xmlTokenId = remember { context.resources.getIdentifier("mapbox_access_token", "string", context.packageName) }
            val xmlToken = remember { if (xmlTokenId != 0) context.resources.getString(xmlTokenId) else null }
            val hasXmlToken = remember { !xmlToken.isNullOrBlank() && !xmlToken.contains("YOUR_MAPBOX") }

            val tokenState = remember {
                mutableStateOf<String?>(BuildConfig.MAPBOX_KEY.ifBlank {
                    prefs.getString("mapbox_token", null) ?: if (hasXmlToken) xmlToken else null
                })
            }

            if (tokenState.value.isNullOrBlank()) {
                KeySetupScreen(onSave = { enteredKey ->
                    prefs.edit().putString("mapbox_token", enteredKey).apply()
                    MapboxOptions.accessToken = enteredKey
                    tokenState.value = enteredKey
                })
            } else {
                tokenState.value?.let { safeToken ->
                    LaunchedEffect(safeToken) { MapboxOptions.accessToken = safeToken }
                }

                var currentPreset by remember { mutableStateOf(LightPresetValue.DAY) }

                val styleConfigState = remember {
                    StandardStyleConfigurationState().apply {
                        lightPreset = currentPreset
                    }
                }

                val mapViewportState = rememberMapViewportState {
                    setCameraOptions {
                        zoom(15.0)
                        center(Point.fromLngLat(101.6869, 3.1390))
                        bearing(0.0)
                        pitch(45.0)
                    }
                }

                var lastFetchTimezone by remember { mutableStateOf<Int?>(null) }
                var lastFetchTime by remember { mutableStateOf(0L) }

                fun shouldFetch(timezoneOffset: Int): Boolean =
                    if (lastFetchTimezone == null) true
                    else {
                        val now = System.currentTimeMillis()
                        (now - lastFetchTime > 5 * 60 * 1000L) || (lastFetchTimezone != timezoneOffset)
                    }

                suspend fun updateLighting(lat: Double, lng: Double, force: Boolean = false) {
                    val timezoneOffset = (lng / 15.0).roundToInt()
                    if (force || shouldFetch(timezoneOffset)) {
                        val weather = fetchWeatherData(lat, lng)
                        if (weather != null) {
                            lastFetchTimezone = timezoneOffset
                            lastFetchTime = System.currentTimeMillis()

                            val now = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                            val timeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US).apply { timeZone = TimeZone.getTimeZone("UTC") }

                            try {
                                val sunriseTime = timeFormat.parse(weather.sunrise)
                                val sunsetTime = timeFormat.parse(weather.sunset)

                                if (sunriseTime != null && sunsetTime != null) {
                                    val nowMs = now.timeInMillis
                                    val sunriseMs = sunriseTime.time
                                    val sunsetMs = sunsetTime.time

                                    val dawnStartMs = sunriseMs - (60 * 60 * 1000)
                                    val duskStartMs = sunsetMs - (30 * 60 * 1000)
                                    val duskEndMs = sunsetMs + (60 * 60 * 1000)

                                    val newPreset = when {
                                        nowMs < dawnStartMs || nowMs > duskEndMs -> LightPresetValue.NIGHT
                                        nowMs < sunriseMs -> LightPresetValue.DAWN
                                        nowMs < duskStartMs -> LightPresetValue.DAY
                                        else -> LightPresetValue.DUSK
                                    }
                                    if (currentPreset != newPreset) {
                                        currentPreset = newPreset
                                        styleConfigState.lightPreset = newPreset
                                    }
                                }
                            } catch (e: Exception) {}
                        }
                    }
                }

                LaunchedEffect(Unit) {
                    delay(1000L)
                    mapViewportState.cameraState?.center?.let { updateLighting(it.latitude(), it.longitude(), true) }
                }

                LaunchedEffect(mapViewportState) {
                    snapshotFlow {
                        val center = mapViewportState.cameraState?.center
                        if (center != null) Pair(center.latitude(), center.longitude()) else null
                    }.debounce(1000L).collectLatest { location ->
                        if (location != null) updateLighting(location.first, location.second, false)
                    }
                }

                LaunchedEffect(Unit) {
                    while (true) {
                        delay(5 * 60 * 1000L)
                        mapViewportState.cameraState?.center?.let { updateLighting(it.latitude(), it.longitude(), true) }
                    }
                }

                MapboxMap(
                    Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState,
                    style = { MapboxStandardStyle(standardStyleConfigurationState = styleConfigState) },
                    scaleBar = { ScaleBar(Modifier.padding(top = 60.dp)) },
                    logo = { Logo(Modifier.padding(bottom = 40.dp)) },
                    attribution = { Attribution(Modifier.padding(bottom = 40.dp)) }
                ) {
                    MapEffect(Unit) { mapView ->
                        mapView.location.updateSettings {
                            locationPuck = createDefault2DPuck(withBearing = true)
                            enabled = true
                            puckBearing = PuckBearing.HEADING
                            pulsingEnabled = true
                            puckBearingEnabled = true
                        }
                        mapViewportState.transitionToFollowPuckState()
                    }

                    MapEffect(currentPreset) { mapView ->
                        mapView.mapboxMap.setStyleImportConfigProperty("basemap", "lightPreset", currentPreset.value)
                    }
                }
            }
        }

        if (!PermissionsManager.areLocationPermissionsGranted(this)) {
            permissionsManager = PermissionsManager(object : PermissionsListener {
                override fun onExplanationNeeded(permissionsToExplain: List<String>) {}
                override fun onPermissionResult(granted: Boolean) {}
            })
            permissionsManager.requestLocationPermissions(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

@Composable
fun KeySetupScreen(onSave: (String) -> Unit) {
    var inputKey by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mapbox API Key Required", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Please enter your Mapbox public access token (starts with pk.).")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputKey,
            onValueChange = { inputKey = it },
            label = { Text("pk.eyJ1...") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSave(inputKey.trim()) },
            modifier = Modifier.fillMaxWidth(),
            enabled = inputKey.startsWith("pk.")
        ) {
            Text("Save & Load Map")
        }
    }
}