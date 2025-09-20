package br.com.brunocarvalhs.howmuch.app.foundation.theme

import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import br.com.brunocarvalhs.howmuch.R

@Composable
fun appLightColors() = lightColorScheme(
    primary = colorResource(R.color.primary),
    primaryContainer = colorResource(R.color.primary_container),
    secondary = colorResource(R.color.secondary_light),
    secondaryContainer = colorResource(R.color.background_light),
    background = colorResource(R.color.background_light),
    surface = colorResource(R.color.surface_light),
    surfaceVariant = colorResource(R.color.surface_light),
    onPrimary = colorResource(R.color.on_primary),
    onSecondary = colorResource(R.color.on_secondary_light),
    onBackground = colorResource(R.color.on_background_light),
    onSurface = colorResource(R.color.on_surface_light)
)

@Composable
fun appDarkColors() = darkColorScheme(
    primary = colorResource(R.color.primary),
    primaryContainer = colorResource(R.color.primary_container),
    secondary = colorResource(R.color.secondary_dark),
    secondaryContainer = colorResource(R.color.surface_dark),
    background = colorResource(R.color.background_dark),
    surface = colorResource(R.color.surface_dark),
    surfaceVariant = colorResource(R.color.surface_dark),
    onPrimary = colorResource(R.color.on_primary),
    onSecondary = colorResource(R.color.on_secondary_dark),
    onBackground = colorResource(R.color.on_background_dark),
    onSurface = colorResource(R.color.on_surface_dark)
)

@Composable
fun HowMuchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> appDarkColors()
        else -> appLightColors()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
