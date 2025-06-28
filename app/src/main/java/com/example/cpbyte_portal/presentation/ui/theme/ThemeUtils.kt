package com.example.cpbyte_portal.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

object CPByteTheme {
    val cardBackground: Color
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) DarkCardBackground else LightCardBackground

    val cardBorder: Color
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) DarkCardBorder else LightCardBorder

    val inputFieldBackground: Color
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) DarkInputFieldBackground else LightInputFieldBackground

    val accentCyan: Color
        @Composable
        @ReadOnlyComposable
        get() = AccentCyan

    val brandCyan: Color
        @Composable
        @ReadOnlyComposable
        get() = CPByteCyan

    val brandBlue: Color
        @Composable
        @ReadOnlyComposable
        get() = CPByteBlue

    fun ColorScheme.surfaceColorAtElevation(
        elevation: Float
    ): Color {
        return if (elevation > 0f) {
            surfaceTint.copy(alpha = elevation * 0.1f).compositeOver(surface)
        } else {
            surface
        }
    }

    val gold: Color = RankGold
    val silver: Color = RankSilver
    val bronze: Color = RankBronze
}