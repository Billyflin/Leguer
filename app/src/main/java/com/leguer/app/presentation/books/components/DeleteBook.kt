package com.leguer.app.presentation.books.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firestorecleanarchitecture.components.ProgressBar
import firestorecleanarchitecture.core.Utils.Companion.print
import firestorecleanarchitecture.domain.model.Response.*
import com.leguer.app.presentation.books.BooksViewModel

@Composable
fun DeleteBook(
    viewModel: BooksViewModel = hiltViewModel()
) {
    when(val deleteBookResponse = viewModel.deleteBookResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> print(deleteBookResponse.e)
    }
}