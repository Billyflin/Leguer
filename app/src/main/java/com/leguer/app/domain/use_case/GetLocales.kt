package com.leguer.app.domain.use_case

import com.leguer.app.domain.repository.LocalesRepository

class GetLocales (
    private val repo: LocalesRepository
) {
    operator fun invoke() = repo.getLocalesFromFirestore()
}