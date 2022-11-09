package com.leguer.app.domain.use_case

import com.leguer.app.domain.use_case.AddBook
import com.leguer.app.domain.use_case.DeleteBook
import com.leguer.app.domain.use_case.GetBooks

data class UseCases (
    val getBooks: GetBooks,
    val addBook: AddBook,
    val deleteBook: DeleteBook
)