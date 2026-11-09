package net.pwindows.pmap.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

enum class PMapTheme {
    YELLOW, GREEN, BLUE, RED, MAGENTA
}

// --- YELLOW ---
private val YellowLightScheme = lightColorScheme(
    primary = yellowPrimaryLight, onPrimary = yellowOnPrimaryLight,
    primaryContainer = yellowPrimaryContainerLight, onPrimaryContainer = yellowOnPrimaryContainerLight,
    secondary = yellowSecondaryLight, onSecondary = yellowOnSecondaryLight,
    secondaryContainer = yellowSecondaryContainerLight, onSecondaryContainer = yellowOnSecondaryContainerLight,
    tertiary = yellowTertiaryLight, onTertiary = yellowOnTertiaryLight,
    tertiaryContainer = yellowTertiaryContainerLight, onTertiaryContainer = yellowOnTertiaryContainerLight,
    error = yellowErrorLight, onError = yellowOnErrorLight,
    errorContainer = yellowErrorContainerLight, onErrorContainer = yellowOnErrorContainerLight,
    background = yellowBackgroundLight, onBackground = yellowOnBackgroundLight,
    surface = yellowSurfaceLight, onSurface = yellowOnSurfaceLight,
    surfaceVariant = yellowSurfaceVariantLight, onSurfaceVariant = yellowOnSurfaceVariantLight,
    outline = yellowOutlineLight, outlineVariant = yellowOutlineVariantLight,
    scrim = yellowScrimLight, inverseSurface = yellowInverseSurfaceLight,
    inverseOnSurface = yellowInverseOnSurfaceLight, inversePrimary = yellowInversePrimaryLight,
    surfaceDim = yellowSurfaceDimLight, surfaceBright = yellowSurfaceBrightLight,
    surfaceContainerLowest = yellowSurfaceContainerLowestLight, surfaceContainerLow = yellowSurfaceContainerLowLight,
    surfaceContainer = yellowSurfaceContainerLight, surfaceContainerHigh = yellowSurfaceContainerHighLight,
    surfaceContainerHighest = yellowSurfaceContainerHighestLight
)

private val YellowDarkScheme = darkColorScheme(
    primary = yellowPrimaryDark, onPrimary = yellowOnPrimaryDark,
    primaryContainer = yellowPrimaryContainerDark, onPrimaryContainer = yellowOnPrimaryContainerDark,
    secondary = yellowSecondaryDark, onSecondary = yellowOnSecondaryDark,
    secondaryContainer = yellowSecondaryContainerDark, onSecondaryContainer = yellowOnSecondaryContainerDark,
    tertiary = yellowTertiaryDark, onTertiary = yellowOnTertiaryDark,
    tertiaryContainer = yellowTertiaryContainerDark, onTertiaryContainer = yellowOnTertiaryContainerDark,
    error = yellowErrorDark, onError = yellowOnErrorDark,
    errorContainer = yellowErrorContainerDark, onErrorContainer = yellowOnErrorContainerDark,
    background = yellowBackgroundDark, onBackground = yellowOnBackgroundDark,
    surface = yellowSurfaceDark, onSurface = yellowOnSurfaceDark,
    surfaceVariant = yellowSurfaceVariantDark, onSurfaceVariant = yellowOnSurfaceVariantDark,
    outline = yellowOutlineDark, outlineVariant = yellowOutlineVariantDark,
    scrim = yellowScrimDark, inverseSurface = yellowInverseSurfaceDark,
    inverseOnSurface = yellowInverseOnSurfaceDark, inversePrimary = yellowInversePrimaryDark,
    surfaceDim = yellowSurfaceDimDark, surfaceBright = yellowSurfaceBrightDark,
    surfaceContainerLowest = yellowSurfaceContainerLowestDark, surfaceContainerLow = yellowSurfaceContainerLowDark,
    surfaceContainer = yellowSurfaceContainerDark, surfaceContainerHigh = yellowSurfaceContainerHighDark,
    surfaceContainerHighest = yellowSurfaceContainerHighestDark
)

// --- GREEN ---
private val GreenLightScheme = lightColorScheme(
    primary = greenPrimaryLight, onPrimary = greenOnPrimaryLight,
    primaryContainer = greenPrimaryContainerLight, onPrimaryContainer = greenOnPrimaryContainerLight,
    secondary = greenSecondaryLight, onSecondary = greenOnSecondaryLight,
    secondaryContainer = greenSecondaryContainerLight, onSecondaryContainer = greenOnSecondaryContainerLight,
    tertiary = greenTertiaryLight, onTertiary = greenOnTertiaryLight,
    tertiaryContainer = greenTertiaryContainerLight, onTertiaryContainer = greenOnTertiaryContainerLight,
    error = greenErrorLight, onError = greenOnErrorLight,
    errorContainer = greenErrorContainerLight, onErrorContainer = greenOnErrorContainerLight,
    background = greenBackgroundLight, onBackground = greenOnBackgroundLight,
    surface = greenSurfaceLight, onSurface = greenOnSurfaceLight,
    surfaceVariant = greenSurfaceVariantLight, onSurfaceVariant = greenOnSurfaceVariantLight,
    outline = greenOutlineLight, outlineVariant = greenOutlineVariantLight,
    scrim = greenScrimLight, inverseSurface = greenInverseSurfaceLight,
    inverseOnSurface = greenInverseOnSurfaceLight, inversePrimary = greenInversePrimaryLight,
    surfaceDim = greenSurfaceDimLight, surfaceBright = greenSurfaceBrightLight,
    surfaceContainerLowest = greenSurfaceContainerLowestLight, surfaceContainerLow = greenSurfaceContainerLowLight,
    surfaceContainer = greenSurfaceContainerLight, surfaceContainerHigh = greenSurfaceContainerHighLight,
    surfaceContainerHighest = greenSurfaceContainerHighestLight
)

private val GreenDarkScheme = darkColorScheme(
    primary = greenPrimaryDark, onPrimary = greenOnPrimaryDark,
    primaryContainer = greenPrimaryContainerDark, onPrimaryContainer = greenOnPrimaryContainerDark,
    secondary = greenSecondaryDark, onSecondary = greenOnSecondaryDark,
    secondaryContainer = greenSecondaryContainerDark, onSecondaryContainer = greenOnSecondaryContainerDark,
    tertiary = greenTertiaryDark, onTertiary = greenOnTertiaryDark,
    tertiaryContainer = greenTertiaryContainerDark, onTertiaryContainer = greenOnTertiaryContainerDark,
    error = greenErrorDark, onError = greenOnErrorDark,
    errorContainer = greenErrorContainerDark, onErrorContainer = greenOnErrorContainerDark,
    background = greenBackgroundDark, onBackground = greenOnBackgroundDark,
    surface = greenSurfaceDark, onSurface = greenOnSurfaceDark,
    surfaceVariant = greenSurfaceVariantDark, onSurfaceVariant = greenOnSurfaceVariantDark,
    outline = greenOutlineDark, outlineVariant = greenOutlineVariantDark,
    scrim = greenScrimDark, inverseSurface = greenInverseSurfaceDark,
    inverseOnSurface = greenInverseOnSurfaceDark, inversePrimary = greenInversePrimaryDark,
    surfaceDim = greenSurfaceDimDark, surfaceBright = greenSurfaceBrightDark,
    surfaceContainerLowest = greenSurfaceContainerLowestDark, surfaceContainerLow = greenSurfaceContainerLowDark,
    surfaceContainer = greenSurfaceContainerDark, surfaceContainerHigh = greenSurfaceContainerHighDark,
    surfaceContainerHighest = greenSurfaceContainerHighestDark
)

// --- BLUE ---
private val BlueLightScheme = lightColorScheme(
    primary = bluePrimaryLight, onPrimary = blueOnPrimaryLight,
    primaryContainer = bluePrimaryContainerLight, onPrimaryContainer = blueOnPrimaryContainerLight,
    secondary = blueSecondaryLight, onSecondary = blueOnSecondaryLight,
    secondaryContainer = blueSecondaryContainerLight, onSecondaryContainer = blueOnSecondaryContainerLight,
    tertiary = blueTertiaryLight, onTertiary = blueOnTertiaryLight,
    tertiaryContainer = blueTertiaryContainerLight, onTertiaryContainer = blueOnTertiaryContainerLight,
    error = blueErrorLight, onError = blueOnErrorLight,
    errorContainer = blueErrorContainerLight, onErrorContainer = blueOnErrorContainerLight,
    background = blueBackgroundLight, onBackground = blueOnBackgroundLight,
    surface = blueSurfaceLight, onSurface = blueOnSurfaceLight,
    surfaceVariant = blueSurfaceVariantLight, onSurfaceVariant = blueOnSurfaceVariantLight,
    outline = blueOutlineLight, outlineVariant = blueOutlineVariantLight,
    scrim = blueScrimLight, inverseSurface = blueInverseSurfaceLight,
    inverseOnSurface = blueInverseOnSurfaceLight, inversePrimary = blueInversePrimaryLight,
    surfaceDim = blueSurfaceDimLight, surfaceBright = blueSurfaceBrightLight,
    surfaceContainerLowest = blueSurfaceContainerLowestLight, surfaceContainerLow = blueSurfaceContainerLowLight,
    surfaceContainer = blueSurfaceContainerLight, surfaceContainerHigh = blueSurfaceContainerHighLight,
    surfaceContainerHighest = blueSurfaceContainerHighestLight
)

private val BlueDarkScheme = darkColorScheme(
    primary = bluePrimaryDark, onPrimary = blueOnPrimaryDark,
    primaryContainer = bluePrimaryContainerDark, onPrimaryContainer = blueOnPrimaryContainerDark,
    secondary = blueSecondaryDark, onSecondary = blueOnSecondaryDark,
    secondaryContainer = blueSecondaryContainerDark, onSecondaryContainer = blueOnSecondaryContainerDark,
    tertiary = blueTertiaryDark, onTertiary = blueOnTertiaryDark,
    tertiaryContainer = blueTertiaryContainerDark, onTertiaryContainer = blueOnTertiaryContainerDark,
    error = blueErrorDark, onError = blueOnErrorDark,
    errorContainer = blueErrorContainerDark, onErrorContainer = blueOnErrorContainerDark,
    background = blueBackgroundDark, onBackground = blueOnBackgroundDark,
    surface = blueSurfaceDark, onSurface = blueOnSurfaceDark,
    surfaceVariant = blueSurfaceVariantDark, onSurfaceVariant = blueOnSurfaceVariantDark,
    outline = blueOutlineDark, outlineVariant = blueOutlineVariantDark,
    scrim = blueScrimDark, inverseSurface = blueInverseSurfaceDark,
    inverseOnSurface = blueInverseOnSurfaceDark, inversePrimary = blueInversePrimaryDark,
    surfaceDim = blueSurfaceDimDark, surfaceBright = blueSurfaceBrightDark,
    surfaceContainerLowest = blueSurfaceContainerLowestDark, surfaceContainerLow = blueSurfaceContainerLowDark,
    surfaceContainer = blueSurfaceContainerDark, surfaceContainerHigh = blueSurfaceContainerHighDark,
    surfaceContainerHighest = blueSurfaceContainerHighestDark
)

// --- RED ---
private val RedLightScheme = lightColorScheme(
    primary = redPrimaryLight, onPrimary = redOnPrimaryLight,
    primaryContainer = redPrimaryContainerLight, onPrimaryContainer = redOnPrimaryContainerLight,
    secondary = redSecondaryLight, onSecondary = redOnSecondaryLight,
    secondaryContainer = redSecondaryContainerLight, onSecondaryContainer = redOnSecondaryContainerLight,
    tertiary = redTertiaryLight, onTertiary = redOnTertiaryLight,
    tertiaryContainer = redTertiaryContainerLight, onTertiaryContainer = redOnTertiaryContainerLight,
    error = redErrorLight, onError = redOnErrorLight,
    errorContainer = redErrorContainerLight, onErrorContainer = redOnErrorContainerLight,
    background = redBackgroundLight, onBackground = redOnBackgroundLight,
    surface = redSurfaceLight, onSurface = redOnSurfaceLight,
    surfaceVariant = redSurfaceVariantLight, onSurfaceVariant = redOnSurfaceVariantLight,
    outline = redOutlineLight, outlineVariant = redOutlineVariantLight,
    scrim = redScrimLight, inverseSurface = redInverseSurfaceLight,
    inverseOnSurface = redInverseOnSurfaceLight, inversePrimary = redInversePrimaryLight,
    surfaceDim = redSurfaceDimLight, surfaceBright = redSurfaceBrightLight,
    surfaceContainerLowest = redSurfaceContainerLowestLight, surfaceContainerLow = redSurfaceContainerLowLight,
    surfaceContainer = redSurfaceContainerLight, surfaceContainerHigh = redSurfaceContainerHighLight,
    surfaceContainerHighest = redSurfaceContainerHighestLight
)

private val RedDarkScheme = darkColorScheme(
    primary = redPrimaryDark, onPrimary = redOnPrimaryDark,
    primaryContainer = redPrimaryContainerDark, onPrimaryContainer = redOnPrimaryContainerDark,
    secondary = redSecondaryDark, onSecondary = redOnSecondaryDark,
    secondaryContainer = redSecondaryContainerDark, onSecondaryContainer = redOnSecondaryContainerDark,
    tertiary = redTertiaryDark, onTertiary = redOnTertiaryDark,
    tertiaryContainer = redTertiaryContainerDark, onTertiaryContainer = redOnTertiaryContainerDark,
    error = redErrorDark, onError = redOnErrorDark,
    errorContainer = redErrorContainerDark, onErrorContainer = redOnErrorContainerDark,
    background = redBackgroundDark, onBackground = redOnBackgroundDark,
    surface = redSurfaceDark, onSurface = redOnSurfaceDark,
    surfaceVariant = redSurfaceVariantDark, onSurfaceVariant = redOnSurfaceVariantDark,
    outline = redOutlineDark, outlineVariant = redOutlineVariantDark,
    scrim = redScrimDark, inverseSurface = redInverseSurfaceDark,
    inverseOnSurface = redInverseOnSurfaceDark, inversePrimary = redInversePrimaryDark,
    surfaceDim = redSurfaceDimDark, surfaceBright = redSurfaceBrightDark,
    surfaceContainerLowest = redSurfaceContainerLowestDark, surfaceContainerLow = redSurfaceContainerLowDark,
    surfaceContainer = redSurfaceContainerDark, surfaceContainerHigh = redSurfaceContainerHighDark,
    surfaceContainerHighest = redSurfaceContainerHighestDark
)

// --- MAGENTA ---
private val MagentaLightScheme = lightColorScheme(
    primary = magentaPrimaryLight, onPrimary = magentaOnPrimaryLight,
    primaryContainer = magentaPrimaryContainerLight, onPrimaryContainer = magentaOnPrimaryContainerLight,
    secondary = magentaSecondaryLight, onSecondary = magentaOnSecondaryLight,
    secondaryContainer = magentaSecondaryContainerLight, onSecondaryContainer = magentaOnSecondaryContainerLight,
    tertiary = magentaTertiaryLight, onTertiary = magentaOnTertiaryLight,
    tertiaryContainer = magentaTertiaryContainerLight, onTertiaryContainer = magentaOnTertiaryContainerLight,
    error = magentaErrorLight, onError = magentaOnErrorLight,
    errorContainer = magentaErrorContainerLight, onErrorContainer = magentaOnErrorContainerLight,
    background = magentaBackgroundLight, onBackground = magentaOnBackgroundLight,
    surface = magentaSurfaceLight, onSurface = magentaOnSurfaceLight,
    surfaceVariant = magentaSurfaceVariantLight, onSurfaceVariant = magentaOnSurfaceVariantLight,
    outline = magentaOutlineLight, outlineVariant = magentaOutlineVariantLight,
    scrim = magentaScrimLight, inverseSurface = magentaInverseSurfaceLight,
    inverseOnSurface = magentaInverseOnSurfaceLight, inversePrimary = magentaInversePrimaryLight,
    surfaceDim = magentaSurfaceDimLight, surfaceBright = magentaSurfaceBrightLight,
    surfaceContainerLowest = magentaSurfaceContainerLowestLight, surfaceContainerLow = magentaSurfaceContainerLowLight,
    surfaceContainer = magentaSurfaceContainerLight, surfaceContainerHigh = magentaSurfaceContainerHighLight,
    surfaceContainerHighest = magentaSurfaceContainerHighestLight
)

private val MagentaDarkScheme = darkColorScheme(
    primary = magentaPrimaryDark, onPrimary = magentaOnPrimaryDark,
    primaryContainer = magentaPrimaryContainerDark, onPrimaryContainer = magentaOnPrimaryContainerDark,
    secondary = magentaSecondaryDark, onSecondary = magentaOnSecondaryDark,
    secondaryContainer = magentaSecondaryContainerDark, onSecondaryContainer = magentaOnSecondaryContainerDark,
    tertiary = magentaTertiaryDark, onTertiary = magentaOnTertiaryDark,
    tertiaryContainer = magentaTertiaryContainerDark, onTertiaryContainer = magentaOnTertiaryContainerDark,
    error = magentaErrorDark, onError = magentaOnErrorDark,
    errorContainer = magentaErrorContainerDark, onErrorContainer = magentaOnErrorContainerDark,
    background = magentaBackgroundDark, onBackground = magentaOnBackgroundDark,
    surface = magentaSurfaceDark, onSurface = magentaOnSurfaceDark,
    surfaceVariant = magentaSurfaceVariantDark, onSurfaceVariant = magentaOnSurfaceVariantDark,
    outline = magentaOutlineDark, outlineVariant = magentaOutlineVariantDark,
    scrim = magentaScrimDark, inverseSurface = magentaInverseSurfaceDark,
    inverseOnSurface = magentaInverseOnSurfaceDark, inversePrimary = magentaInversePrimaryDark,
    surfaceDim = magentaSurfaceDimDark, surfaceBright = magentaSurfaceBrightDark,
    surfaceContainerLowest = magentaSurfaceContainerLowestDark, surfaceContainerLow = magentaSurfaceContainerLowDark,
    surfaceContainer = magentaSurfaceContainerDark, surfaceContainerHigh = magentaSurfaceContainerHighDark,
    surfaceContainerHighest = magentaSurfaceContainerHighestDark
)

@Composable
fun PMapTheme(
    theme: PMapTheme = PMapTheme.YELLOW,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        theme == PMapTheme.YELLOW -> if (darkTheme) YellowDarkScheme else YellowLightScheme
        theme == PMapTheme.GREEN -> if (darkTheme) GreenDarkScheme else GreenLightScheme
        theme == PMapTheme.BLUE -> if (darkTheme) BlueDarkScheme else BlueLightScheme
        theme == PMapTheme.RED -> if (darkTheme) RedDarkScheme else RedLightScheme
        theme == PMapTheme.MAGENTA -> if (darkTheme) MagentaDarkScheme else MagentaLightScheme
        else -> if (darkTheme) YellowDarkScheme else YellowLightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}