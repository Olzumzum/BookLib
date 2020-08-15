package com.olzumzum.booklib.server

import com.olzumzum.booklib.model.BookResponse
import com.olzumzum.booklib.model.BooksByDateResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BookApi {
    @GET("lists/names.json?api-key=$API_KEY")
    fun getAllBooks(): Single<BookResponse>

    @GET("lists/2019-01-20/hardcover-fiction.json?api-key=$API_KEY")
    fun getBooksByDate(): Single<BooksByDateResponse>

    companion object{
        private const val API_KEY = "DbxVWARey07rfVhGOu3Arypl5Lftc76g"
    }
}

