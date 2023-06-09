package com.leguer.app.domain.repository

import kotlinx.coroutines.flow.Flow
import com.leguer.app.domain.model.Book
import firestorecleanarchitecture.domain.model.Response

typealias Books = List<Book>
typealias BooksResponse = Response<Books>
typealias AddBookResponse = Response<Boolean>
typealias DeleteBookResponse = Response<Boolean>

interface BooksRepository {
    fun getBooksFromFirestore(): Flow<BooksResponse>

    suspend fun addBookToFirestore(title: String, author: String): AddBookResponse

    suspend fun deleteBookFromFirestore(bookId: String): DeleteBookResponse
}