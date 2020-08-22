package com.olzumzum.booklib.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.InfoBooksByDate
import kotlinx.coroutines.CoroutineExceptionHandler

@Database(entities = arrayOf(BookX::class, InfoBooksByDate::class), version = 1, exportSchema = false)
abstract class NYTDatabase: RoomDatabase() {
    abstract fun bookByDateDao() : BookByDateDao
}