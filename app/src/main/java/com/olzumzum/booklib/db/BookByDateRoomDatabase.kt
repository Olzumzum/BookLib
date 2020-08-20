package com.olzumzum.booklib.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olzumzum.booklib.model.BookX

@Database(entities = arrayOf(BookX::class), version = 1, exportSchema = false)
abstract class BookByDateRoomDatabase: RoomDatabase() {
    abstract fun
}