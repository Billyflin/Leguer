package com.leguer.app.presentation.locales.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.leguer.app.presentation.locales.LocalesViewModel
import firestorecleanarchitecture.components.ProgressBar
import firestorecleanarchitecture.core.Utils.Companion.print
import firestorecleanarchitecture.domain.model.Response.*

@Composable
fun AddLocal(
    viewModel: LocalesViewModel = hiltViewModel()
) {
    when(val addBookResponse = viewModel.addLocalResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> print(addBookResponse.e)
    }
}