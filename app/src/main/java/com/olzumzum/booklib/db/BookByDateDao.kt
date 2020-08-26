package com.olzumzum.booklib.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.olzumzum.booklib.model.InfoBooksByDate


@Dao
interface BookByDateDao {

    @Query("SELECT * FROM info_books_by_date")
    suspend fun getAllInfoBooks(): List<InfoBooksByDate>

    @Query("SELECT * FROM info_books_by_date where bestsellers_date like :period ")
    fun getInfoBooksById(period: String): LiveData<InfoBooksByDate>

    @Query("SELECT Exists ( SELECT * FROM info_books_by_date where bestsellers_date like :period)")
    suspend fun availabilityRecord(period: String): Int

    @Query("SELECT count(*) FROM info_books_by_date where bestsellers_date like :period")
    suspend fun countRecord(period: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInfo(infoWithBooks: InfoBooksByDate): Long

    @Query("DELETE FROM info_books_by_date")
    suspend fun deleteInfoBooksAll()
}