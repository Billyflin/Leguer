package com.leguer.app.presentation.locales.components

//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.material.AlertDialog
//import androidx.compose.material.Text
//import androidx.compose.material.TextButton
//import androidx.compose.material.TextField
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.unit.dp
//import firestorecleanarchitecture.core.Constants
//import kotlinx.coroutines.job

//@Composable
//fun AddLocalAlertDialog(
//    openDialog: Boolean,
//    closeDialog: () -> Unit,
//    addLocal: (name: String, address: String, lat: Double, long: Double, state: String, city: String) -> Unit
//) {
//    if (openDialog) {
//        var title by remember { mutableStateOf(Constants.NO_VALUE) }
//        var author by remember { mutableStateOf(Constants.NO_VALUE) }
//        val focusRequester = FocusRequester()
//
//        AlertDialog(
//            onDismissRequest = closeDialog,
//            title = {
//                Text(
//                    text = Constants.ADD_BOOK
//                )
//            },
//            text = {
//                Column {
//                    TextField(
//                        value = title,
//                        onValueChange = { title = it },
//                        placeholder = {
//                            Text(
//                                text = Constants.BOOK_TITLE
//                            )
//                        },
//                        modifier = Modifier.focusRequester(focusRequester)
//                    )
//                    LaunchedEffect(Unit) {
//                        coroutineContext.job.invokeOnCompletion {
//                            focusRequester.requestFocus()
//                        }
//                    }
//                    Spacer(
//                        modifier = Modifier.height(16.dp)
//                    )
//                    TextField(
//                        value = author,
//                        onValueChange = { author = it },
//                        placeholder = {
//                            Text(
//                                text = Constants.AUTHOR
//                            )
//                        }
//                    )
//                }
//            },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        closeDialog()
//                        addBook(title, author)
//                    }
//                ) {
//                    Text(
//                        text = Constants.ADD
//                    )
//                }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = closeDialog
//                ) {
//                    Text(
//                        text = Constants.DISMISS
//                    )
//                }
//            }
//        )
//    }
//
//}
