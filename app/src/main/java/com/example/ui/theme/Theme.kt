package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.model.AppLanguage

private val VanteraColorScheme = lightColorScheme(
    primary = MidnightNavy,
    onPrimary = PureWhite,
    primaryContainer = MidnightNavySoft,
    onPrimaryContainer = PureWhite,
    secondary = Terracotta,
    onSecondary = PureWhite,
    secondaryContainer = TerracottaTint,
    onSecondaryContainer = MidnightNavy,
    tertiary = Graphite,
    onTertiary = PureWhite,
    background = PureWhite,
    onBackground = Graphite,
    surface = PureWhite,
    onSurface = Graphite,
    surfaceVariant = WarmStone,
    onSurfaceVariant = Graphite,
    surfaceContainerLowest = PureWhite,
    surfaceContainerLow = WarmStoneLight,
    surfaceContainer = WarmStone,
    outline = WarmStoneBorder,
    outlineVariant = WarmStoneBorder,
    error = ErrorCrimson,
    onError = PureWhite,
    errorContainer = ErrorTint,
    onErrorContainer = ErrorCrimson,
)

private val VanteraShapes = Shapes(
    extraSmall = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(8.dp),
    extraLarge = RoundedCornerShape(10.dp),
)

@Composable
fun VanteraTheme(
    language: AppLanguage = AppLanguage.EN,
    content: @Composable () -> Unit,
) {
    val typography = remember(language) { createVanteraTypography(language) }
    val layoutDirection = if (language.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        MaterialTheme(
            colorScheme = VanteraColorScheme,
            typography = typography,
            shapes = VanteraShapes,
            content = content,
        )
    }
}
