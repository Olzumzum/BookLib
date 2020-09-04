package com.olzumzum.booklib.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.db.BookByDateDao
import com.olzumzum.booklib.model.dto.Category
import com.olzumzum.booklib.model.dto.InfoBooksByDate
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.model.pojo.InfoBook
import com.olzumzum.booklib.model.pojo.InfoWithBooks
import com.olzumzum.booklib.server.BookServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class BookRepository(
    private val service: BookServerCommunicator,
    private val dao: BookByDateDao
) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getAllBook(): Single<List<Category>> = service.getAllCategory()

    /**
     * вернуть информацию о списке бестселлеров
     */
    fun getInfoBook(): LiveData<InfoWithBooks>? {
        val period: String = "2020-08-01"
        refreshInfoBooks(period)
        return dao.getInfoBooksById(period)

    }

    /**
     * вернуть список бестселлеров
     */
    fun getBooks(): LiveData<List<BookX>>? {
        return dao.getBooks()
    }

    /**
     * удалить все элементы в базе
     */
    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            dao.deleteAllBooks()
            dao.deleteInfoBooksAll()
        }
    }

    /**
     * проверить, есть ли данные с таким id
     * ЗАМЕНИТЬ ПРОВЕРКУ ПО ИД НА ПРОВЕРКУ ПО ДАТЕ
     */
    private fun refreshInfoBooks(period: String) = GlobalScope.launch(Dispatchers.IO) {
        var countRecord = 0
        //если в кеш есть запись с такими данными вернуть ее
        countRecord = dao.countRecord(period)

        if (countRecord == 0) {

            //иначе сделать запрос на сервер
            //получить данные
            //сохранить их в кеш
            //вернуть данные из кеша
            disposable.add(
                service.getBooksByDate()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<InfoBooksByDate>() {
                        override fun onSuccess(info: InfoBooksByDate) {
                            val job = GlobalScope.launch(Dispatchers.IO) {
                                val infoBook: InfoBook = InfoBook(
                                    0,
                                    bestsellersDate = info.bestsellersDate,
                                    displayName = info.displayName,
                                    nextPublishedDate = info.nextPublishedDate,
                                    normalListEndsAt = info.normalListEndsAt,
                                    previousPublishedDate = info.previousPublishedDate,
                                    publishedDate = info.publishedDate,
                                    publishedDateDescription = info.publishedDateDescription,
                                    updated = info.updated
                                )

                                //вставить информацию о книгах из списка бестселлеров
                                info.books.forEach { book ->
                                    book.idInfo = dao.insertInfo(infoBook)
                                    dao.insertBook(book)
                                }
                            }
                        }

                        override fun onError(e: Throwable) {
                            Log.e(TAG, "Data loading error, ${e.message}")
                            throw e
                        }
                    })
            )
        }
    }

    /**
     * отписаться от всех источников
     */
    fun clear(){
        disposable.dispose()
    }

    companion object {
        private const val TAG = "MyLog"
    }

}