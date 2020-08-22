package com.olzumzum.booklib.server

import android.app.Application
import com.olzumzum.booklib.model.Category
import com.olzumzum.booklib.model.InfoBooksByDate
import io.reactivex.Single


class BookServerCommunicator(val mBookApi: BookApi, val application: Application) {


    fun getAllBook(): Single<List<Category>> {
        val v = application.applicationContext
        return mBookApi.getAllBooks()
            .flatMap { response ->
                Single.just(response.categories)
            }
    }

    fun getBooksByDate(): Single<InfoBooksByDate> {
        return mBookApi.getBooksByDate()
            .flatMap { response ->
                Single.just(response.infoBooksByDate)
            }
    }
}