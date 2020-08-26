package com.olzumzum.booklib.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.olzumzum.booklib.model.InfoBooksByDate


@Dao
interface BookByDateDao {
    @Query("SELECT * FROM info_books_by_date where id like :id")
    fun getInfoBooksById(id: Long): LiveData<InfoBooksByDate>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInfo(infoWithBooks: InfoBooksByDate): Long

    @Query("DELETE FROM info_books_by_date")
    suspend fun deleteInfoBooksAll()
}