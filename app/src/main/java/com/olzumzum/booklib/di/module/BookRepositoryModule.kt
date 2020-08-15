package com.olzumzum.booklib.di.module

import com.olzumzum.booklib.repository.BookRepository
import com.olzumzum.booklib.server.BookServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class BookRepositoryModule {

    @Provides
    fun provideBookRepository(bookServerCommunicator: BookServerCommunicator): BookRepository{
        return BookRepository(bookServerCommunicator)
    }
}