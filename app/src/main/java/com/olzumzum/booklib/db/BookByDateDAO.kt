package com.olzumzum.booklib.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.InfoBooksByDate

@Dao
interface BookByDateDAO {
    @Query("SELECT * FROM ")
    fun getBookByDate(): LiveData<InfoBooksByDate>

    suspend fun insertBook(book: BookX)
}