package com.leguer.app.presentation.locales

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.leguer.app.R
import com.leguer.app.presentation.locales.components.*
import firestorecleanarchitecture.core.Constants

@Composable
fun LocalesScreen(
    viewModel: LocalesViewModel = hiltViewModel()
) {
    val initialZoom = 12f
    val destinationLatLng = LatLng(-33.437148, -70.632175)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(destinationLatLng, initialZoom)
    }
    val context = LocalContext.current
    var lat by remember { mutableStateOf(0.0) }
    var long by remember { mutableStateOf(0.0) }
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL, isMyLocationEnabled = false))
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Locales() { locales4 ->
            Log.d("Locales Change",locales4.toString())
            GoogleMap(modifier = Modifier.fillMaxSize(),
                uiSettings = uiSettings,
                properties=properties,
                cameraPositionState = cameraPositionState,
                onMapLongClick = {
                    viewModel.openDialog()
                    lat = it.latitude
                    long = it.longitude
                },
                content = {
                    locales4.forEach {
                        Marker(
                            title = it.name, snippet = it.address, state = rememberMarkerState(
                                position = LatLng(it.lat!!, it.long!!)
                            ), icon = bitmapDescriptorFromVector(
                                context, R.drawable.ic_baseline_place_24
                            )
                        )
                    }
                    AddLocalAlertDialog(lat,
                        long,
                        openDialog = viewModel.openDialog,
                        closeDialog = {
                            viewModel.closeDialog()
                        },
                        addLocal = { name, address, lat, long, state, city ->
                            viewModel.addBook(name, address, lat, long, state, city)
                        })
                })
        }
        Switch(
            checked = uiSettings.zoomControlsEnabled,
            onCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
            }
        )
    }
    AddLocal()
    DeleteLocal()
}

private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
