package com.olzumzum.booklib.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.olzumzum.booklib.model.pojo.InfoBook
import com.olzumzum.booklib.model.pojo.InfoWithBooks


@Dao
interface BookByDateDao {

    @Query("SELECT * FROM info_books_by_date")
    suspend fun getAllInfoBooks(): List<InfoWithBooks>

    @Query("SELECT * FROM info_books_by_date where bestsellers_date like :period ")
    fun getInfoBooksById(period: String): LiveData<InfoWithBooks>

    @Query("SELECT Exists ( SELECT * FROM info_books_by_date where bestsellers_date like :period)")
    suspend fun availabilityRecord(period: String): Int

    @Query("SELECT count(*) FROM info_books_by_date where bestsellers_date like :period")
    suspend fun countRecord(period: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInfo(infoWithBooks: InfoBook): Long

    @Query("DELETE FROM info_books_by_date")
    suspend fun deleteInfoBooksAll()
}