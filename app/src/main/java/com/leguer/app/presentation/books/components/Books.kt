package com.leguer.app.presentation.books.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firestorecleanarchitecture.components.ProgressBar
import firestorecleanarchitecture.core.Utils.Companion.print
import firestorecleanarchitecture.domain.model.Response.*
import com.leguer.app.domain.repository.Books
import com.leguer.app.presentation.books.BooksViewModel

@Composable
fun Books(
    viewModel: BooksViewModel = hiltViewModel(),
    booksContent: @Composable (books: Books) -> Unit
) {
    when(val booksResponse = viewModel.booksResponse) {
        is Loading -> ProgressBar()
        is Success -> booksContent(booksResponse.data)
        is Failure -> print(booksResponse.e)
    }
}