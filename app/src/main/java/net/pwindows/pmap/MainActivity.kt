package net.pwindows.pmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import net.pwindows.pmap.ui.theme.PMapTheme
import com.mapbox.geojson.Point
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.interpolate
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.linear
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.attribution.Attribution
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.scalebar.ScaleBar
import com.mapbox.maps.plugin.locationcomponent.R

class MainActivity : ComponentActivity() {
    lateinit var permissionsManager: PermissionsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mapViewportState = rememberMapViewportState()
            MapboxMap(
                Modifier.fillMaxSize(),
                mapViewportState = mapViewportState,
                scaleBar = {
                    ScaleBar(Modifier.padding(top = 60.dp))
                },
                logo = {
                    Logo(Modifier.padding(bottom = 40.dp))
                },
                attribution = {
                    Attribution(Modifier.padding(bottom = 40.dp))
                },
            ) {
                MapEffect(Unit) { mapView ->
                    mapView.location.updateSettings {
                        locationPuck = LocationPuck2D(
                            bearingImage = ImageHolder.from(R.drawable.mapbox_user_puck_icon),
                            shadowImage = ImageHolder.from(R.drawable.mapbox_user_icon_shadow),
                            scaleExpression = interpolate {
                                linear()
                                zoom()
                                stop {
                                    literal(0.0)
                                    literal(0.5)   // 50% at zoom 0
                                }
                                stop {
                                    literal(20.0)
                                    literal(0.7)   // 70% at zoom 20
                                }
                            }.toJson()
                        )
                        enabled = true
                        puckBearing = PuckBearing.HEADING
                        pulsingEnabled = true
                        puckBearingEnabled = true
                    }
                    mapViewportState.transitionToFollowPuckState()
                }
            }
        }

        if (PermissionsManager.areLocationPermissionsGranted(this))
        {

        } else {
            permissionsManager = PermissionsManager(object : PermissionsListener {
                override fun onExplanationNeeded(permissionsToExplain: List<String>) {}
                override fun onPermissionResult(granted: Boolean) {
                    if (granted) {

                    }
                }
            })
            permissionsManager.requestLocationPermissions(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}