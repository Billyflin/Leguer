package com.leguer.app.presentation.locales

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.leguer.app.components.TopBar
import com.leguer.app.presentation.locales.components.*

@Composable
fun LocalesScreen(
    viewModel: LocalesViewModel = hiltViewModel()
) {
    val initialZoom = 2f
    val destinationLatLng = LatLng(-33.437148, -70.632175)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(destinationLatLng, initialZoom)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            cameraPositionState = cameraPositionState,
            content = {
                Locales(
                    localesContent = {
                            locales ->
                        LocalesContent(
                            locales = locales,
                            deleteBook = { bookId ->
                                viewModel.deleteBook(bookId)
                            }
                        )
                        AddLocalAlertDialog(
                            openDialog = viewModel.openDialog,
                            closeDialog = {
                                viewModel.closeDialog()
                            },
                            addLocal = { name, address, lat, long, state, city ->
                                viewModel.addBook(name, address, lat, long, state, city)
                            }
                        )
                    }
                )
            }
        )
//        Scaffold(
//            topBar = {
//                TopBar()
//            },
//            content = { padding ->
//                Locales(
//                    localesContent = { locales ->
//                        LocalesContent(
//                            locales = locales,
//                            deleteBook = { bookId ->
//                                viewModel.deleteBook(bookId)
//                            }
//                        )
//                        AddLocalAlertDialog(
//                            openDialog = viewModel.openDialog,
//                            closeDialog = {
//                                viewModel.closeDialog()
//                            },
//                            addLocal = { name, address, lat, long, state, city ->
//                                viewModel.addBook(name, address, lat, long, state, city)
//                            }
//                        )
//                    }
//                )
//            },
//            floatingActionButton = {
//                AddLocalFloatingActionButton(
//                    openDialog = {
//                        viewModel.openDialog()
//                    }
//                )
//            }
//        )
    }
    AddLocal()
    DeleteLocal()
}