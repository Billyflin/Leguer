package com.leguer.app.domain.use_case

import com.leguer.app.domain.repository.BooksRepository

class DeleteBook(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(bookId: String) = repo.deleteBookFromFirestore(bookId)
}