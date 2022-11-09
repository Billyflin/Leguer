package com.leguer.app.presentation.locales.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import firestorecleanarchitecture.core.Constants
import kotlinx.coroutines.job

@Composable
fun AddLocalAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addLocal: (name: String, address: String, lat: Double, long: Double, state: String, city: String) -> Unit
) {
    if (openDialog) {
        var name by remember { mutableStateOf(Constants.NO_VALUE) }
        var address by remember { mutableStateOf(Constants.NO_VALUE) }
        var lat by remember { mutableStateOf(0.0) }
        var long by remember { mutableStateOf(0.0) }
        var state by remember { mutableStateOf(Constants.NO_VALUE) }
        var city by remember { mutableStateOf(Constants.NO_VALUE) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = Constants.ADD_BOOK
                )
            },
            text = {
                Column {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = {
                            Text(
                                text = Constants.LOCAL_NAME
                            )
                        },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = address,
                        onValueChange = { address = it },
                        placeholder = {
                            Text(
                                text = Constants.LOCAL_ADDRESS
                            )
                        }
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = long.toString(),
                        onValueChange = {
                            if (it.isEmpty()){
                                long = it.toDouble()
                            } else {
                                long = when (it.toDoubleOrNull()) {
                                    null -> long //old value
                                    else -> it.toDouble()   //new value
                                }
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = lat.toString(),
                        onValueChange = {
                            if (it.isEmpty()){
                                lat = it.toDouble()
                            } else {
                                lat = when (it.toDoubleOrNull()) {
                                    null -> lat //old value
                                    else -> it.toDouble()   //new value
                                }
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = state,
                        onValueChange = { state = it },
                        placeholder = {
                            Text(
                                text = Constants.LOCAL_STATE
                            )
                        }
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = city,
                        onValueChange = { city = it },
                        placeholder = {
                            Text(
                                text = Constants.LOCAL_CITY
                            )
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        addLocal(name,address,lat,long,state,city)
                    }
                ) {
                    Text(
                        text = Constants.ADD
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = closeDialog
                ) {
                    Text(
                        text = Constants.DISMISS
                    )
                }
            }
        )
    }

}
