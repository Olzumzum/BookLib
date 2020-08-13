package com.olzumzum.booklib.server

import android.util.Log
import com.olzumzum.bookslibrary.model.Book
import com.olzumzum.bookslibrary.model.BookResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit


class BookServerCommunicator(val mBookApi: BookApi) {



    fun getAllBook(): Single<Book> {
        return  mBookApi.getAllBook()
            .flatMap {
                Single.just(it.result)
            }

//        return k
    }
}