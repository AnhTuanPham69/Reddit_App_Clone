package com.example.redditapp.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
  primary = RwPrimary,
  primaryVariant = RwPrimaryDark,
  onPrimary = Color.Gray,
  secondary = Color.LightGray,
  secondaryVariant = RwPrimaryDark,
  onSecondary = Color.Black,
  error = Red800
)

private val DarkThemeColors = darkColors(
  primary = RwPrimaryDark,
  primaryVariant = RwPrimary,
  onPrimary = Color.Gray,
  secondary = Color.Black,
  onSecondary = Color.White,
  error = Red800
)

@Composable
fun JetRedditTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colors = if (JetRedditThemeSettings.isInDarkTheme.value) DarkThemeColors else LightThemeColors,
    content = content
  )
}


object JetRedditThemeSettings {
  var isInDarkTheme: MutableState<Boolean> = mutableStateOf(false)
}