package com.leguer.app.presentation.locales.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import com.leguer.app.R
import com.leguer.app.domain.repository.Locales

@Composable
fun LocalesContent(
    locales: Locales,
    deleteBook: (bookId: String) -> Unit
) {
    val context = LocalContext.current
//    LazyColumn(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(
//            items = locales
//        ) { local ->
//            LocalCard(
//                local = local,
//                deleteBook = {
//                    local.id?.let { localId ->
//                        deleteBook(localId)
//                    }
//                }
//            )
//        }
//    }
    locales.forEach {

        Marker(
            title = it.name, snippet = it.address, state = rememberMarkerState(
                position = LatLng(it.lat!!, it.long!!)
            ), icon = bitmapDescriptorFromVector(
                context, R.drawable.ic_baseline_place_24
            )
        )
    }
}


private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
