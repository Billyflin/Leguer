package com.leguer.app.presentation.locales.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leguer.app.domain.repository.Locales

@Composable
fun LocalesContent(
    padding: PaddingValues,
    books: Locales,
    deleteBook: (bookId: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = books
        ) { local ->
            LocalCard(
                local = local,
                deleteBook = {
                    local.id?.let { localId ->
                        deleteBook(localId)
                    }
                }
            )
        }
    }
}