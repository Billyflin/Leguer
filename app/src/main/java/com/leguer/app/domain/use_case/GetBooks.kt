package com.leguer.app.domain.use_case

import com.leguer.app.domain.repository.BooksRepository

class GetBooks (
    private val repo: BooksRepository
) {
    operator fun invoke() = repo.getBooksFromFirestore()
}