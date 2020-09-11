package com.olzumzum.booklib.server

import com.olzumzum.booklib.model.dto.CategoriesResponse
import com.olzumzum.booklib.model.dto.BooksByDateResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {
    @GET("lists/names.json?api-key=$API_KEY")
    fun getAllBooks(): Single<CategoriesResponse>

    @GET("lists/{period}/hardcover-fiction.json?api-key=$API_KEY")
    fun getBooksByDate(@Path("period")period: String): Single<BooksByDateResponse>

    companion object{
        private const val API_KEY = "DbxVWARey07rfVhGOu3Arypl5Lftc76g"
    }
}

