package com.leguer.app.presentation.locales

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.leguer.app.components.TopBar
import com.leguer.app.presentation.locales.components.*

@Composable
fun LocalesScreen(
    viewModel: LocalesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Locales(
                localesContent = { books ->
                    LocalesContent(
                        padding = padding,
                        books = books,
                        deleteBook = { bookId ->
                            viewModel.deleteBook(bookId)
                        }
                    )
                    AddLocalAlertDialog(
                        openDialog = viewModel.openDialog,
                        closeDialog = {
                            viewModel.closeDialog()
                        },
                        addLocal = { name, address, lat, long, state, city ->
                            viewModel.addBook(name, address, lat, long, state, city)
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            AddLocalFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )
    AddLocal()
    DeleteLocal()
}