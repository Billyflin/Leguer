package com.leguer.app.presentation.profile.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import com.leguer.app.core.Constants.MOSTRAR_COMUNAS
import com.leguer.app.core.Constants.MOSTRAR_MARCADORES
import com.leguer.app.core.Constants.PROFILE_SCREEN
import com.leguer.app.core.Constants.REVOKE_ACCESS
import com.leguer.app.core.Constants.SIGN_OUT

@Composable
fun ProfileTopBar(
    signOut: () -> Unit,
    revokeAccess: () -> Unit,
    toggleMarker: () -> Unit,
    toggleComuna: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = PROFILE_SCREEN
            )
        },
        actions = {
            IconButton(
                onClick = {
                    openMenu = !openMenu
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = {
                    openMenu = !openMenu
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = SIGN_OUT
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        revokeAccess()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = REVOKE_ACCESS
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        toggleMarker()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = MOSTRAR_MARCADORES
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        toggleComuna()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = MOSTRAR_COMUNAS
                    )
                }
            }
        }
    )
}