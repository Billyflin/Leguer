package com.leguer.app.presentation.locales

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils.HSLToColor
import androidx.core.graphics.ColorUtils.setAlphaComponent
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
    viewModel: LocalesViewModel = hiltViewModel(),
    padding: PaddingValues,
    markerVisible:Boolean,
    comunaVisible:Boolean
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
                    Marker(title = local.name, snippet = local.address,
                        visible = markerVisible,
                        state = MarkerState(
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
                        }
                    )
                }
                viewModel.comunas.forEach { punto ->
//                    Log.d("local", locales4.toString())
                    Polygon(
                        visible = comunaVisible,
                        fillColor =
                        contarMarcadores(locales4, punto.puntosLat),
                        points = punto.puntosLat,
                        tag = punto.name,
                        onClick = {
                            Toast.makeText(context, punto.name, Toast.LENGTH_SHORT).show()
                        },
                        clickable = true,
                        strokeWidth = 2f
                    )

                }
                AddLocalAlertDialog(
                    lat,
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
    }
    AddLocal()
    DeleteLocal()
}

fun contarMarcadores(locals: Locales, vertices: List<LatLng>): Color {
    var intersectCount = 0
    var totales = 0
    locals.forEach { local ->
        if (PolyUtil.containsLocation(local.lat!!, local.long!!, vertices, false)) {
            totales++
            if (local.state.equals("full")) {
                intersectCount++
            } else if (local.state.equals("empty")) {
                intersectCount--
            }
        }
    }
    val colorReal: Double = if (totales != 0) {
        (intersectCount.toDouble() / totales.toDouble()) * 60
    } else {
        0.0
    }
//        Log.d("real",colorReal.toString())

    if (totales != 0) {
        val hsl = floatArrayOf((60 + colorReal).toFloat(), 0.5f, 0.5f)
        return Color(setAlphaComponent(HSLToColor(hsl), 120))
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
