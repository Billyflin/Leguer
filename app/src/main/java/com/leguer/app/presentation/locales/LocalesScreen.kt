package com.leguer.app.presentation.locales

//import androidx.compose.material.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.leguer.app.components.TopBar

//@Composable
//fun LocalesScreen(
//    viewModel: LocalesViewModel = hiltViewModel()
//) {
//    Scaffold(
//        topBar = {
//            TopBar()
//        },
//        content = { padding ->
//            Books(
//                booksContent = { books ->
//                    BooksContent(
//                        padding = padding,
//                        books = books,
//                        deleteBook = { bookId ->
//                            viewModel.deleteBook(bookId)
//                        }
//                    )
//                    AddLocalAlertDialog(
//                        openDialog = viewModel.openDialog,
//                        closeDialog = {
//                            viewModel.closeDialog()
//                        },
//                        addLocal = { title, author ->
//                            viewModel.addBook(title, author)
//                        }
//                    )
//                }
//            )
//        },
//        floatingActionButton = {
//            AddBookFloatingActionButton(
//                openDialog = {
//                    viewModel.openDialog()
//                }
//            )
//        }
//    )
//    AddBook()
//    DeleteBook()
//}