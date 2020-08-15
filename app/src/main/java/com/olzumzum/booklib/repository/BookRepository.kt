package com.olzumzum.booklib.repository

import android.util.Log
import com.olzumzum.booklib.server.BookApi
import com.olzumzum.booklib.server.BookServerCommunicator
import com.olzumzum.bookslibrary.model.Book
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class BookRepository(
    val bookServerCommunicator: BookServerCommunicator
) {

    fun getAllBook(): Single<Book> = bookServerCommunicator.getAllBook()


}