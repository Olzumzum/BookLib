package com.olzumzum.booklib.repository

import android.util.Log
import com.olzumzum.booklib.db.BookByDateDao
import com.olzumzum.booklib.model.Category
import com.olzumzum.booklib.model.InfoBooksByDate
import com.olzumzum.booklib.server.BookServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BookRepository (
    private val service: BookServerCommunicator,
    private val cache: BookByDateDao
) {

    fun getAllBook(): Single<List<Category>> = service.getAllBook()

    fun getBooksByDate(): Single<InfoBooksByDate> {
        var idInfo: Int? = null
        val dispos = service.getBooksByDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<InfoBooksByDate>() {
                override fun onSuccess(t: InfoBooksByDate) {
                    runBlocking {
                        idInfo = cache.insertInfo(t)

                    }
                }

                override fun onError(e: Throwable) {
                    throw Exception("Error loading data from server")
                }

            })
        Log.e("lof", "Id value $idInfo")

        return service.getBooksByDate()
//        return null
    }

}