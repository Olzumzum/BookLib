package com.olzumzum.booklib.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.InfoBooksByDate

@Dao
interface BookByDateDao {
    @Query("SELECT * FROM info_books_by_date")
    fun getBookByDate(): LiveData<InfoBooksByDate>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBook(book: BookX)

    @Query("SELECT * FROM bookX where id_bookX = :id")
    fun getBookX(id: Int)
}