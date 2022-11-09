package firestorecleanarchitecture.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import firestorecleanarchitecture.core.Constants.BOOKS
import com.leguer.app.repository.BooksRepositoryImpl
import com.leguer.app.domain.repository.BooksRepository
import com.leguer.app.domain.use_case.AddBook
import com.leguer.app.domain.use_case.DeleteBook
import com.leguer.app.domain.use_case.GetBooks
import com.leguer.app.domain.use_case.UseCases

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//    @Provides
//    fun provideFirebaseFirestore() = Firebase.firestore

//    @Provides
//    fun provideBooksRef(
//        db: FirebaseFirestore
//    ) = db.collection(BOOKS)
//
//    @Provides
//    fun provideBooksRepository(
//        booksRef: CollectionReference
//    ): BooksRepository = BooksRepositoryImpl(booksRef)
//
//    @Provides
//    fun provideUseCases(
//        repo: BooksRepository
//    ) = UseCases(
//        getBooks = GetBooks(repo),
//        addBook = AddBook(repo),
//        deleteBook = DeleteBook(repo)
//    )
//}