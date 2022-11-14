package com.leguer.app.domain.model


import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class Comuna(
    @SerializedName("name")
    val name: String,
    @SerializedName("puntos")
    val puntos: List<List<Double>>,
    var puntosLat: List<LatLng>
)