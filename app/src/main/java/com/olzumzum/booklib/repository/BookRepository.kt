package com.olzumzum.booklib.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.olzumzum.booklib.db.BookByDateDao
import com.olzumzum.booklib.model.Category
import com.olzumzum.booklib.model.InfoBooksByDate
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

    fun getBooksByDate(): LiveData<InfoBooksByDate> {
        val id: Long = 3
         refreshInfoBooks(id)
        return dao.getInfoBooksById(id)

    }

    private fun cashData() {
        var idRecord: Long = 0
        val disposable = service.getBooksByDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<InfoBooksByDate>() {
                override fun onSuccess(info: InfoBooksByDate) {
                    val job = GlobalScope.launch(Dispatchers.IO) {
                        idRecord = dao.insertInfo(info)
                        Log.e(TAG, "id record is $idRecord")

                        val saveData =
                            dao.getInfoBooksById(idRecord)

                        Log.e(TAG, "saved data ${saveData}")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "Data loading error, ${e.message}")
                    throw e
                }

            })
    }

    /**
     * проверить, есть ли данные с таким id
     * ЗАМЕНИТЬ ПРОВЕРКУ ПО ИД НА ПРОВЕРКУ ПО ДАТЕ
     */
    private fun refreshInfoBooks(id: Long){
        //если в кеш есть запись с такими данными вернуть ее
        var dataCache: LiveData<InfoBooksByDate> = dao.getInfoBooksById(id)


        //иначе сделать запрос на сервер
        //получить данные
        //сохранить их в кеш
        //вернуть данные из кеша
        val disposable = service.getBooksByDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<InfoBooksByDate>() {
                override fun onSuccess(info: InfoBooksByDate) {
                    val job = GlobalScope.launch(Dispatchers.IO) {
                       dao.insertInfo(info)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "Data loading error, ${e.message}")
                    throw e
                }

            })

    }


    companion object {
        private const val TAG = "MyLog"
    }

}