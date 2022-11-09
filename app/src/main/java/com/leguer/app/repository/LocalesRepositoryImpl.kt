package com.leguer.app.repository

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import firestorecleanarchitecture.core.Constants.NAME
import com.leguer.app.domain.model.Local
import com.leguer.app.domain.repository.*
import firestorecleanarchitecture.domain.model.Response.Failure
import firestorecleanarchitecture.domain.model.Response.Success
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class LocalesRepositoryImpl @Inject constructor(
    @Named("locales") private val localesRef: CollectionReference
) : LocalesRepository {
    override fun getLocalesFromFirestore() = callbackFlow {
        val snapshotListener = localesRef.orderBy(NAME).addSnapshotListener { snapshot, e ->
            val localesResponse = if (snapshot != null) {
                val locales = snapshot.toObjects(Local::class.java)
                Success(locales)
            } else {
                Failure(e)
            }
            trySend(localesResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }


    override suspend fun addLocalToFirestore(
        name: String, address: String, lat: Double, long: Double, state: String, city: String
    ): AddLocalResponse {
        return try {
            val id = localesRef.document().id
            val local = Local(
                id = id,
                name = name,
                address = address,
                state = state,
                lat = lat,
                long = long,
                city = city
            )
            localesRef.document(id).set(local).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun deleteLocalFromFirestore(localId: String): DeleteLocalResponse {
        return try {
            localesRef.document(localId).delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}