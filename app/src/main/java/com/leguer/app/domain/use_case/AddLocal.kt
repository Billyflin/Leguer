package com.leguer.app.domain.use_case

import com.leguer.app.domain.repository.LocalesRepository

class AddLocal(
    private val repo: LocalesRepository
) {
    suspend operator fun invoke(name: String, address: String,lat: Double,long: Double,state: String,city: String
    ) = repo.addLocalToFirestore(name,address,lat,long,state,city)
}