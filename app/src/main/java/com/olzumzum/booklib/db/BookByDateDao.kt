package com.olzumzum.booklib.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.model.pojo.InfoBook
import com.olzumzum.booklib.model.pojo.InfoWithBooks


@Dao
interface BookByDateDao {

    /**
     * вернуть все записи информации о списках бестселлеров
     * и книгах в них
     */
    @Transaction
    @Query("SELECT * FROM info_books_by_date ")
     fun getAllInfoBooks(): List<InfoWithBooks>

    /** вернуть информацию о списках бестселлеров и книг в них
     * по заданному периоду
     */
    @Query("SELECT * FROM info_books_by_date where bestsellers_date like :period ")
    fun getInfoBooksById(period: String): LiveData<InfoWithBooks>

    @Query("SELECT Exists ( SELECT * FROM info_books_by_date where bestsellers_date like :period)")
    suspend fun availabilityRecord(period: String): Int

    /** вернуть количество записей в таблице информации о списках бестселлеров
     * по указанному периоду
     */
    @Query("SELECT count(*) FROM info_books_by_date where bestsellers_date like :period")
    suspend fun countRecord(period: String): Int

    @Query("SELECT count(*) FROM bookX")
     fun countBooks(): Int

    @Query("SELECT * FROM bookX")
    fun getBooks(): LiveData<List<BookX>>

    @Query("SELECT * FROM bookX where id = :id")
    fun getBookById(id: Long): LiveData<BookX>?

    @Query("SELECT * FROM info_books_by_date")
    fun getAllInfo(): List<InfoBook>

    /** добавить информацию о списке бестселлеров */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfo(infoBook: InfoBook): Long

    /** добавить книги из списка бестселлеров */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBook(book: BookX): Long

    /** очистить таблицы информации о списках бестселлеров
     * и книг в этих списках
     */
    @Query("DELETE FROM info_books_by_date")
    suspend fun deleteInfoBooksAll()

    @Query("DELETE FROM bookX")
    suspend fun deleteAllBooks()
}