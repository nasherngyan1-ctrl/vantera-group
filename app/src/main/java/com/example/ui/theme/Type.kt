package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.AppLanguage

val PlusJakartaSansFamily = FontFamily(
    Font(R.font.plus_jakarta_sans, FontWeight.Light),
    Font(R.font.plus_jakarta_sans, FontWeight.Normal),
    Font(R.font.plus_jakarta_sans, FontWeight.Medium),
    Font(R.font.plus_jakarta_sans, FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans, FontWeight.Bold),
)

val IbmPlexArabicFamily = FontFamily(
    Font(R.font.ibm_plex_sans_arabic, FontWeight.Light),
    Font(R.font.ibm_plex_sans_arabic, FontWeight.Normal),
    Font(R.font.ibm_plex_sans_arabic, FontWeight.Medium),
    Font(R.font.ibm_plex_sans_arabic, FontWeight.SemiBold),
    Font(R.font.ibm_plex_sans_arabic, FontWeight.Bold),
)

val NotoSansArabicFamily = FontFamily(
    Font(R.font.noto_sans_arabic, FontWeight.Light),
    Font(R.font.noto_sans_arabic, FontWeight.Normal),
    Font(R.font.noto_sans_arabic, FontWeight.Medium),
    Font(R.font.noto_sans_arabic, FontWeight.SemiBold),
    Font(R.font.noto_sans_arabic, FontWeight.Bold),
)

fun fontFamilyFor(language: AppLanguage): FontFamily = when (language) {
    AppLanguage.EN -> PlusJakartaSansFamily
    AppLanguage.AR -> IbmPlexArabicFamily
    AppLanguage.KU -> NotoSansArabicFamily
}

fun createVanteraTypography(language: AppLanguage): Typography {
    val family = fontFamilyFor(language)
    val isRtl = language.isRtl

    return Typography(
        displayLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = if (isRtl) 34.sp else 38.sp,
            lineHeight = if (isRtl) 46.sp else 44.sp,
            letterSpacing = if (isRtl) 0.sp else (-0.6).sp,
        ),
        displayMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = if (isRtl) 28.sp else 32.sp,
            lineHeight = if (isRtl) 40.sp else 38.sp,
            letterSpacing = if (isRtl) 0.sp else (-0.4).sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = if (isRtl) 25.sp else 28.sp,
            lineHeight = if (isRtl) 36.sp else 34.sp,
            letterSpacing = if (isRtl) 0.sp else (-0.3).sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = if (isRtl) 22.sp else 23.sp,
            lineHeight = if (isRtl) 32.sp else 30.sp,
            letterSpacing = if (isRtl) 0.sp else (-0.2).sp,
        ),
        titleLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = 19.sp,
            lineHeight = if (isRtl) 28.sp else 26.sp,
            letterSpacing = 0.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = if (isRtl) 25.sp else 22.sp,
            letterSpacing = if (isRtl) 0.sp else 0.1.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = if (isRtl) 28.sp else 26.sp,
            letterSpacing = if (isRtl) 0.sp else 0.15.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = if (isRtl) 25.sp else 22.sp,
            letterSpacing = if (isRtl) 0.sp else 0.15.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = if (isRtl) 21.sp else 18.sp,
            letterSpacing = if (isRtl) 0.sp else 0.2.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = if (isRtl) 0.sp else 0.4.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = if (isRtl) 0.sp else 0.8.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = if (isRtl) 0.sp else 1.2.sp,
        ),
    )
}
