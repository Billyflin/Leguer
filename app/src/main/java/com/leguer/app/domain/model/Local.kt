package com.leguer.app.domain.model

data class Local(
    var id: String? = null,
    var name: String? = null,
    var address: String? = null,
    val state: String? = null,
    val lat: Double? = null,
    val long: Double? = null,
    val city: String? = null
)
