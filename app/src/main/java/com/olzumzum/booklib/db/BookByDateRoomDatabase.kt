package com.olzumzum.booklib.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olzumzum.booklib.model.BookX
import kotlinx.coroutines.CoroutineExceptionHandler

@Database(entities = arrayOf(BookX::class), version = 1, exportSchema = false)
abstract class BookByDateRoomDatabase: RoomDatabase() {
    abstract fun bookByDateDao() : BookByDateDao

    companion object {
        @Volatile
        private var INSTANCE: BookByDateRoomDatabase? = null

        fun getDatabase(context: Context): BookByDateRoomDatabase? {
            val tempInstance = INSTANCE
            tempInstance.let {
                return tempInstance
            }

            val handler = CoroutineExceptionHandler { _, exception ->
                println("Caught during database creation --> $exception")
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookByDateRoomDatabase::class.java,
                    ""
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}