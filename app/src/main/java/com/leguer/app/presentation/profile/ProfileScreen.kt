package com.leguer.app.presentation.profile

import androidx.compose.material.*
import androidx.compose.material.SnackbarResult.ActionPerformed
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.leguer.app.core.Constants.REVOKE_ACCESS_MESSAGE
import com.leguer.app.core.Constants.SIGN_OUT
import com.leguer.app.presentation.locales.LocalesScreen
import com.leguer.app.presentation.profile.components.ProfileContent
import com.leguer.app.presentation.profile.components.ProfileTopBar
import com.leguer.app.presentation.profile.components.RevokeAccess
import com.leguer.app.presentation.profile.components.SignOut
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var markerVisible by remember { mutableStateOf(true) }
    var comunaVisible by remember { mutableStateOf(false) }

    BottomSheetScaffold(
        topBar = {
            ProfileTopBar(
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                },
                toggleComuna = {
                    comunaVisible = !comunaVisible
                },
                toggleMarker = {
                    markerVisible = !markerVisible
                }
            )
        },
        content = { padding ->
            LocalesScreen(
                padding = padding,
                markerVisible = markerVisible,
                comunaVisible = comunaVisible
            )
        },
        scaffoldState = scaffoldState,
        sheetContent = {
            ProfileContent(
                photoUrl = viewModel.photoUrl,
                displayName = viewModel.displayName
            )
        }
    )
    SignOut(
        navigateToAuthScreen = { signedOut ->
            if (signedOut) {
                navigateToAuthScreen()
            }
        }
    )

    fun showSnackBar() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == ActionPerformed) {
            viewModel.signOut()
        }
    }

    RevokeAccess(
        navigateToAuthScreen = { accessRevoked ->
            if (accessRevoked) {
                navigateToAuthScreen()
            }
        },
        showSnackBar = {
            showSnackBar()
        }
    )
}