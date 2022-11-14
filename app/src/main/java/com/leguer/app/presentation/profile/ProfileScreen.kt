package com.leguer.app.presentation.profile

import androidx.compose.material.*
import androidx.compose.material.SnackbarResult.ActionPerformed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import com.leguer.app.core.Constants.REVOKE_ACCESS_MESSAGE
import com.leguer.app.core.Constants.SIGN_OUT
import com.leguer.app.presentation.locales.LocalesScreen
import com.leguer.app.presentation.profile.components.ProfileContent
import com.leguer.app.presentation.profile.components.ProfileTopBar
import com.leguer.app.presentation.profile.components.RevokeAccess
import com.leguer.app.presentation.profile.components.SignOut

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = {
            ProfileTopBar(
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            LocalesScreen(padding=padding)
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