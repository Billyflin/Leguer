package com.leguer.app.presentation.locales.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import firestorecleanarchitecture.core.Constants.NO_VALUE
import com.leguer.app.domain.model.Local

@Composable
fun LocalCard(
    local: Local,
    deleteBook: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                TextTitle(
                    bookTitle = local.name ?: NO_VALUE
                )
                TextAuthor(
                    bookAuthor = local.id ?: NO_VALUE
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            DeleteIcon(
                deleteBook = deleteBook
            )
        }
    }
}