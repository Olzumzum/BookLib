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

    companion object {
        @Volatile
        private var INSTANCE: NYTDatabase? = null

        fun getDatabase(context: Context): NYTDatabase {
            val tempInstance = INSTANCE
            tempInstance.let {
                return tempInstance!!
            }

            val handler = CoroutineExceptionHandler { _, exception ->
                println("Caught during database creation --> $exception")
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NYTDatabase::class.java,
                    "nyt_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}