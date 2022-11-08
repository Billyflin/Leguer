package com.leguer.app.navigation

import com.leguer.app.core.Constants.AUTH_SCREEN
import com.leguer.app.core.Constants.PROFILE_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}