package com.leguer.app.presentation.locales

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.android.PolyUtil
import com.leguer.app.domain.model.Comuna
import com.leguer.app.domain.model.Comunas
import com.leguer.app.repository.comunas_json2
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComunasViewModel @Inject constructor(
) : ViewModel() {
    var comunas: List<Comuna> = emptyList()


    init {
        getComunas()
        convertToComuna()
    }

    private fun convertToComuna() {
        comunas.forEach { punto ->
            val data = mutableListOf<LatLng>()
            punto.puntos.forEach {
                data.add(LatLng(it[1], it[0]))
            }
            punto.puntosLat = data
//            Log.d("DATA",punto.name+ data.toString())
            Log.d("dentro",
                isPointInPolygon(LatLng(-33.5959577937964,-70.8119952597983),punto.puntosLat).toString()
            )
        }

    }

    private fun isPointInPolygon(tap: LatLng, vertices: List<LatLng>): Boolean {
        var intersectCount = 0
        for (j in 0 until (vertices.size - 1)) {
            if (rayCastIntersect(tap, vertices[j], vertices[j + 1])) {
                intersectCount++
            }
            Log.d("intersect",intersectCount.toString())
        }
        return intersectCount % 2 == 1 // odd = inside, even = outside;
    }

    private fun rayCastIntersect(tap: LatLng, vertA: LatLng, vertB: LatLng): Boolean {
        val aY = vertA.latitude
        val bY = vertB.latitude
        val aX = vertA.longitude
        val bX = vertB.longitude
        val pY = tap.latitude
        val pX = tap.longitude
        if (aY > pY && bY > pY || aY < pY && bY < pY
            || aX < pX && bX < pX
        ) {
            return false // a and b can't both be above or below pt.y, and a or
            // b must be east of pt.x
        }
        val m = (aY - bY) / (aX - bX) // Rise over run
        val bee = -aX * m + aY // y = mx + b
        val x = (pY - bee) / m // algebra is neat!
        return x > pX
    }
    fun getComunas() {
        val testModel = Gson().fromJson(comunas_json2, Comunas::class.java)
        comunas = testModel.comunas
//        testModel.comunas.forEach {
//            Log.d("Nombre",it.name)
//            it.puntos.forEach{punto->
//
//                Log.d("puntos", punto[1].toString())
//            }
//        }
//       Log.d("Comunas",testModel.toString())
    }

}