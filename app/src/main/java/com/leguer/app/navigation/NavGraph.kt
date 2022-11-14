package com.leguer.app.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.leguer.app.navigation.Screen.AuthScreen
import com.leguer.app.navigation.Screen.ProfileScreen
import com.leguer.app.presentation.auth.AuthScreen
import com.leguer.app.presentation.profile.ProfileScreen
import com.leguer.app.ui.theme.LeguerTheme

@Composable
@ExperimentalAnimationApi
fun NavGraph(
    navController: NavHostController
) {
    LeguerTheme(
    ) {
        AnimatedNavHost(navController = navController,
            startDestination = AuthScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            composable(
                route = AuthScreen.route
            ) {
                AuthScreen(navigateToProfileScreen = {
                    navController.navigate(ProfileScreen.route)
                })
            }
            composable(
                route = ProfileScreen.route
            ) {
                LeguerTheme() {
                    ProfileScreen(navigateToAuthScreen = {
                        navController.popBackStack()
                        navController.navigate(AuthScreen.route)
                    })
                }
            }
        }
    }
}
