package com.olzumzum.booklib.server

import android.app.Application
import com.olzumzum.booklib.model.dto.Category
import com.olzumzum.booklib.model.dto.InfoBooksByDate
import com.olzumzum.booklib.model.pojo.InfoBook
import io.reactivex.Single


class BookServerCommunicator(private val mBookApi: BookApi, private val application: Application) {

    /**
     * получить категории литературы
     */
    fun getAllCategory(): Single<List<Category>> {
        val v = application.applicationContext
        return mBookApi.getAllBooks()
            .flatMap { response ->
                Single.just(response.categories)
            }
    }

    /**
     * получить список бестселлеров по дате
     */
    fun getBooksByDate(): Single<InfoBooksByDate> {
        return mBookApi.getBooksByDate()
            .flatMap { response ->
                Single.just(response.infoBooksByDate)
            }
    }
}