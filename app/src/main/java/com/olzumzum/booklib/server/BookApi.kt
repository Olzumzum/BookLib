package com.olzumzum.booklib.server

import com.olzumzum.booklib.model.dto.CategoriesResponse
import com.olzumzum.booklib.model.dto.BooksByDateResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BookApi {
    @GET("lists/names.json?api-key=$API_KEY")
    fun getAllBooks(): Single<CategoriesResponse>

    @GET("lists/2020-08-15/hardcover-fiction.json?api-key=$API_KEY")
    fun getBooksByDate(): Single<BooksByDateResponse>

    companion object{
        private const val API_KEY = "DbxVWARey07rfVhGOu3Arypl5Lftc76g"
    }
}

