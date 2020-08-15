package com.olzumzum.booklib.server

import com.olzumzum.booklib.model.BookResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BookApi {
    @GET("lists/names.json?api-key=DbxVWARey07rfVhGOu3Arypl5Lftc76g")
    fun getAllBook(): Single<BookResponse>

    companion object{
        private val API_KEY = "DbxVWARey07rfVhGOu3Arypl5Lftc76g"
    }
}

