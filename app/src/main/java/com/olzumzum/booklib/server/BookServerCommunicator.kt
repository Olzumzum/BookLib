package com.olzumzum.booklib.server

import android.util.Log
import com.olzumzum.booklib.model.Book
import com.olzumzum.booklib.model.BookResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class BookServerCommunicator(val mBookApi: BookApi) {


    fun getAllBook(): Single<List<Book>> {
        return mBookApi.getAllBook()
            .flatMap { response ->
                Single.just(response.books)
            }


    }
}