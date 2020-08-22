package com.olzumzum.booklib.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.InfoBooksByDate
import io.reactivex.Single

@Dao
interface BookByDateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBook(book: BookX)

    @Query("SELECT * FROM bookX where id = :id")
    fun getBookX(id: Int): LiveData<List<BookX>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInfo(infoBooksByDate: InfoBooksByDate): Int = infoBooksByDate.id

    @Query("SELECT * FROM info_books_by_date where id = :id")
    fun getIndfo(id :Int): LiveData<InfoBooksByDate>
}