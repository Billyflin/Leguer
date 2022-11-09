package com.leguer.app.presentation.locales.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import firestorecleanarchitecture.components.ProgressBar
import firestorecleanarchitecture.core.Utils.Companion.print
import firestorecleanarchitecture.domain.model.Response.*
import com.leguer.app.domain.repository.Locales
import com.leguer.app.presentation.locales.LocalesViewModel

@Composable
fun Locales(
    viewModel: LocalesViewModel = hiltViewModel(),
    localesContent: @Composable (books: Locales) -> Unit
) {
    when(val localesResponse = viewModel.localesResponse) {
        is Loading -> ProgressBar()
        is Success -> localesContent(localesResponse.data)
        is Failure -> print(localesResponse.e)
    }
}