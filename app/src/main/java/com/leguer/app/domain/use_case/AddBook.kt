package com.leguer.app.domain.use_case

import com.leguer.app.domain.repository.BooksRepository

class AddBook(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(
        title: String,
        author: String
    ) = repo.addBookToFirestore(title, author)
}