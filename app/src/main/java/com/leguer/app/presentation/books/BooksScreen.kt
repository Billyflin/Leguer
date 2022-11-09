package com.leguer.app.presentation.books

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.leguer.app.components.TopBar
import com.leguer.app.presentation.books.components.*

@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Books(
                booksContent = { books ->
                    BooksContent(
                        padding = padding,
                        books = books,
                        deleteBook = { bookId ->
                            viewModel.deleteBook(bookId)
                        }
                    )
                    AddBookAlertDialog(
                        openDialog = viewModel.openDialog,
                        closeDialog = {
                            viewModel.closeDialog()
                        },
                        addBook = { title, author ->
                            viewModel.addBook(title, author)
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            AddBookFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )
    AddBook()
    DeleteBook()
}