package com.leguer.app.presentation.locales

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import com.google.maps.android.compose.*
import com.leguer.app.R
import com.leguer.app.domain.repository.Locales
import com.leguer.app.presentation.locales.components.AddLocal
import com.leguer.app.presentation.locales.components.AddLocalAlertDialog
import com.leguer.app.presentation.locales.components.DeleteLocal
import com.leguer.app.presentation.locales.components.Locales

@Composable
fun LocalesScreen(
    viewModel: LocalesViewModel = hiltViewModel(), padding: PaddingValues
) {
    val initialZoom = 12f
    val destinationLatLng = LatLng(-33.437148, -70.632175)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(destinationLatLng, initialZoom)
    }
    val context = LocalContext.current
    var lat by remember { mutableStateOf(0.0) }
    var long by remember { mutableStateOf(0.0) }

    Box(
        Modifier.fillMaxSize()
    ) {
        Locales { locales4 ->
//            Log.d("Locales Change", locales4.toString())
            GoogleMap(modifier = Modifier.fillMaxSize(), uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                compassEnabled = false,
                myLocationButtonEnabled = false,
                mapToolbarEnabled = false
            ), cameraPositionState = cameraPositionState, onMapLongClick = {
                viewModel.openDialog()
                lat = it.latitude
                long = it.longitude
            }, content = {
                locales4.forEach { local ->
                    Marker(title = local.name, snippet = local.address, state = MarkerState(
                        position = LatLng(local.lat!!, local.long!!)
                    ), icon = if (local.state.equals("empty")) {
                        bitmapDescriptorFromVector(
                            context, R.drawable.ic_baseline_location_off_24
                        )
                    } else if (local.state.equals("full")) {
                        bitmapDescriptorFromVector(
                            context, R.drawable.ic_baseline_add_location_24
                        )
                    } else {
                        bitmapDescriptorFromVector(context, R.drawable.ic_baseline_place_24)
                    }, onInfoWindowLongClick = {
                        local.id?.let { it1 ->
                            viewModel.deleteBook(
                                it1
                            )
                        }
                    })
                }
                    viewModel.comunas.forEach { punto ->
                        Log.d("local", locales4.toString())
                        Polygon(

                            fillColor = colorPolyline(
                                contarMarcadores(locales4, punto.puntosLat)
                            ), points = punto.puntosLat, tag = punto.name, onClick = {
                                Toast.makeText(context, punto.name, Toast.LENGTH_SHORT).show()
                            }, clickable = true,
                            strokeWidth = 0.7f
                        )

                    }
                AddLocalAlertDialog(lat, long, openDialog = viewModel.openDialog, closeDialog = {
                    viewModel.closeDialog()
                }, addLocal = { name, address, lat, long, state, city ->
                    viewModel.addBook(name, address, lat, long, state, city)
                })
            })
        }
    }
    AddLocal()
    DeleteLocal()
}

fun contarMarcadores(locals: Locales, vertices: List<LatLng>): Int {
    var intersectCount = 0
    locals.forEach {
        local ->
    if (PolyUtil.containsLocation(local.lat!!, local.long!!, vertices, false)) {
        if (local.state.equals("full")){
            intersectCount++
        } else if (local.state.equals("empty")){
            intersectCount--
        }
        Log.d("intersect", intersectCount.toString())

    }

    }
    return intersectCount
}

fun colorPolyline(number: Int?): Color {
    if (number == 0) {
        return Color.Transparent
    } else if (number!! >= 1) {
        return Color(red = 0f, blue = 0f, green = 1f, alpha = 0.3f)
    } else if (number >= -1) {
        return Color(red = 1f, blue = 0f, green = 0f, alpha = 0.3f)
    }
    return Color.Transparent
}

private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
