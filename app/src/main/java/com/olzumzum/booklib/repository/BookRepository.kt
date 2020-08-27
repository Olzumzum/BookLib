package com.olzumzum.booklib.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.olzumzum.booklib.db.BookByDateDao
import com.olzumzum.booklib.model.dto.Category
import com.olzumzum.booklib.model.pojo.InfoBook
import com.olzumzum.booklib.server.BookServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class BookRepository(
    private val service: BookServerCommunicator,
    private val dao: BookByDateDao
) {

    fun getAllBook(): Single<List<Category>> = service.getAllCategory()

    fun getBooksByDate(): LiveData<InfoBook>? {
        val period: String = "2020-08-01"
        deleteAll()
        refreshInfoBooks(period)
        Log.e(TAG, "text ")
//        return dao.getInfoBooksById(period)
        return null
    }

    /**
     * удалить все элементы в базе
     */
    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            dao.deleteInfoBooksAll()
        }
    }

    private fun getAll(){
        GlobalScope.launch {
            val list = dao.getAllInfoBooks()
            list.forEach {
                Log.e(TAG, "$it")
            }

            Log.e(TAG, "value ${list.size}")
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
        Log.e(TAG, "Count of record is $countRecord")

        if (countRecord == 0) {
            Log.e(TAG, "Count of record is $countRecord")

            //иначе сделать запрос на сервер
            //получить данные
            //сохранить их в кеш
            //вернуть данные из кеша
            val disposable = service.getBooksByDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<InfoBook>() {
                    override fun onSuccess(info: InfoBook) {
                        val job = GlobalScope.launch(Dispatchers.IO) {
                            dao.insertInfo(info)
                            Log.e(TAG, "value list ${dao.getAllInfoBooks().size}")
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Data loading error, ${e.message}")
                        throw e
                    }
                })
        }
    }

    companion object {
        private const val TAG = "MyLog"
    }

}