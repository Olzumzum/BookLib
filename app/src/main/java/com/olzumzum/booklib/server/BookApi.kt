package com.olzumzum.booklib.server

import com.olzumzum.bookslibrary.model.BookResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BookApi {
    @GET("api/query.json?type=/type/edition&authors=/authors/OL1A")
    fun getAllBook(): Single<BookResponse>
}