package com.leguer.app.presentation.locales

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import firestorecleanarchitecture.domain.model.Response.Loading
import firestorecleanarchitecture.domain.model.Response.Success
import com.leguer.app.domain.repository.AddLocalResponse
import com.leguer.app.domain.repository.LocalesResponse
import com.leguer.app.domain.repository.DeleteLocalResponse
import com.leguer.app.domain.use_case.UseCases2
import javax.inject.Inject

@HiltViewModel
class LocalesViewModel @Inject constructor(
    private val useCases: UseCases2
): ViewModel() {
    var localesResponse by mutableStateOf<LocalesResponse>(Loading)
        private set
    var addLocalResponse by mutableStateOf<AddLocalResponse>(Success(false))
        private set
    var deleteLocalResponse by mutableStateOf<DeleteLocalResponse>(Success(false))
        private set
    var openDialog by mutableStateOf(false)
        private set


    init {
        getLocales()
    }

    private fun getLocales() = viewModelScope.launch {
        useCases.getLocales().collect { response ->
            localesResponse = response
        }
    }

    fun addBook(name: String, address: String, lat: Double, long: Double, state: String, city: String) = viewModelScope.launch {
        addLocalResponse = Loading
        addLocalResponse = useCases.addLocal(name, address, lat, long, state, city)
    }

    fun deleteBook(bookId: String) = viewModelScope.launch {
        deleteLocalResponse = Loading
        deleteLocalResponse = useCases.deleteLocal(bookId)
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}