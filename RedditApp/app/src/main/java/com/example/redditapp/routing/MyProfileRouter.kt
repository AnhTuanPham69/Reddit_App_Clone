package com.example.redditapp.routing

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Class defining the screens we have in the app.
 *
 * These objects should match files we have in the screens package
 */
sealed class MyProfileScreenType {
  object Posts : MyProfileScreenType()
  object About : MyProfileScreenType()
}

object MyProfileRouter {
  var currentScreen: MutableState<MyProfileScreenType> = mutableStateOf(MyProfileScreenType.Posts)

  fun navigateTo(destination: MyProfileScreenType) {
    currentScreen.value = destination
  }
}