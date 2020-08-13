package com.olzumzum.booklib.repository

import android.util.Log
import com.olzumzum.booklib.server.BookApi
import com.olzumzum.booklib.server.BookServerCommunicator
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository() {
    private val bookServerCommunicator: BookServerCommunicator
    init {

        val retrofit = provideRetrofit()
        val api = provideBookApi(retrofit)

        bookServerCommunicator = BookServerCommunicator(api)
    }
    private var sibleAllBook: Single<String>? = null
    var d: String? = null

    fun getAllBook(): Single<String>? {

        bookServerCommunicator.getAllBook()
//        sibleAllBook = bookServerCommunicator.getAllBook()
//        .flatMap {
//            Log.e("MyLog", "Loading is ${it.isSuccessful}")
//            d = it.body()?.status
//            Single.just(it.body()?.status)
//        }
        return sibleAllBook
    }


    companion object {
        private val API_URL = "http://openlibrary.org"

        fun provideRetrofit(): Retrofit {
            val networkLogInterceptor = HttpLoggingInterceptor()
            networkLogInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(networkLogInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        fun provideBookApi(retrofit: Retrofit): BookApi {
            return retrofit.create(BookApi::class.java)
        }
    }
}