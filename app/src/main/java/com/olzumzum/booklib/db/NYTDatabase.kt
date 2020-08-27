package com.olzumzum.booklib.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.model.pojo.InfoBook

@Database(entities = arrayOf(BookX::class, InfoBook::class), version = 1, exportSchema = false)
abstract class NYTDatabase: RoomDatabase() {
    abstract fun bookByDateDao() : BookByDateDao
}