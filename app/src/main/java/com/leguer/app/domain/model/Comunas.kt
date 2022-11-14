package com.leguer.app.domain.model


import com.google.gson.annotations.SerializedName

data class Comunas(
    @SerializedName("Comunas:")
    val comunas: List<Comuna>
)