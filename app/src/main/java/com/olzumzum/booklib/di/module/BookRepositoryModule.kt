package com.olzumzum.booklib.di.module

import android.app.Application
import androidx.room.Room
import com.olzumzum.booklib.db.BookByDateDao
import com.olzumzum.booklib.db.NYTDatabase
import com.olzumzum.booklib.repository.BookRepository
import com.olzumzum.booklib.server.BookServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class BookRepositoryModule {

    @Provides
    fun provideBookRepository(
        bookServerCommunicator: BookServerCommunicator
        ,bookByDateDao: BookByDateDao
    ): BookRepository {
        return BookRepository(bookServerCommunicator
            , bookByDateDao
        )
    }

    @Provides
    fun provideNYTDatabase(application: Application): NYTDatabase {
        return Room.databaseBuilder(application.applicationContext,
            NYTDatabase::class.java,
            "nyt_db").build()
    }

    @Provides
    fun provideBookByDateDao(db: NYTDatabase): BookByDateDao {
        return db.bookByDateDao()
    }
}