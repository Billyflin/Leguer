package com.leguer.app.domain.use_case

import com.leguer.app.domain.repository.LocalesRepository

class DeleteLocal(
    private val repo: LocalesRepository
) {
    suspend operator fun invoke(bookId: String) = repo.deleteLocalFromFirestore(bookId)
}