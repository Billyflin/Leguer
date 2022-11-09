package com.leguer.app.domain.repository

import com.leguer.app.domain.model.Local
import firestorecleanarchitecture.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Locales = List<Local>
typealias LocalesResponse = Response<Locales>
typealias AddLocalResponse = Response<Boolean>
typealias DeleteLocalResponse = Response<Boolean>

interface LocalesRepository {
    fun getLocalesFromFirestore(): Flow<LocalesResponse>

    suspend fun addLocalToFirestore(name: String, address: String,lat: Double,long: Double,state: String,city: String): AddBookResponse

    suspend fun deleteLocalFromFirestore(localId: String): DeleteBookResponse
}