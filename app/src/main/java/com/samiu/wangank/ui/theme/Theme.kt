package com.samiu.wangank.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * @author samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
private val wanDarkColorScheme = darkColorScheme(
    primary = wanDarkPrimary,
    onPrimary = wanDarkOnPrimary,
    primaryContainer = wanDarkPrimaryContainer,
    onPrimaryContainer = wanDarkOnPrimaryContainer,
    inversePrimary = wanDarkPrimaryInverse,
    secondary = wanDarkSecondary,
    onSecondary = wanDarkOnSecondary,
    secondaryContainer = wanDarkSecondaryContainer,
    onSecondaryContainer = wanDarkOnSecondaryContainer,
    tertiary = wanDarkTertiary,
    onTertiary = wanDarkOnTertiary,
    tertiaryContainer = wanDarkTertiaryContainer,
    onTertiaryContainer = wanDarkOnTertiaryContainer,
    error = wanDarkError,
    onError = wanDarkOnError,
    errorContainer = wanDarkErrorContainer,
    onErrorContainer = wanDarkOnErrorContainer,
    background = wanDarkBackground,
    onBackground = wanDarkOnBackground,
    surface = wanDarkSurface,
    onSurface = wanDarkOnSurface,
    inverseSurface = wanDarkInverseSurface,
    inverseOnSurface = wanDarkInverseOnSurface,
    surfaceVariant = wanDarkSurfaceVariant,
    onSurfaceVariant = wanDarkOnSurfaceVariant,
    outline = wanDarkOutline
)

private val wanLightColorScheme = lightColorScheme(
    primary = wanLightPrimary,
    onPrimary = wanLightOnPrimary,
    primaryContainer = wanLightPrimaryContainer,
    onPrimaryContainer = wanLightOnPrimaryContainer,
    inversePrimary = wanLightPrimaryInverse,
    secondary = wanLightSecondary,
    onSecondary = wanLightOnSecondary,
    secondaryContainer = wanLightSecondaryContainer,
    onSecondaryContainer = wanLightOnSecondaryContainer,
    tertiary = wanLightTertiary,
    onTertiary = wanLightOnTertiary,
    tertiaryContainer = wanLightTertiaryContainer,
    onTertiaryContainer = wanLightOnTertiaryContainer,
    error = wanLightError,
    onError = wanLightOnError,
    errorContainer = wanLightErrorContainer,
    onErrorContainer = wanLightOnErrorContainer,
    background = wanLightBackground,
    onBackground = wanLightOnBackground,
    surface = wanLightSurface,
    onSurface = wanLightOnSurface,
    inverseSurface = wanLightInverseSurface,
    inverseOnSurface = wanLightInverseOnSurface,
    surfaceVariant = wanLightSurfaceVariant,
    onSurfaceVariant = wanLightOnSurfaceVariant,
    outline = wanLightOutline
)

@Composable
fun WanAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val wanColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> wanDarkColorScheme
        else -> wanLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = wanColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = wanColorScheme,
        typography = wanTypography,
        shapes = shapes,
        content = content
    )
}