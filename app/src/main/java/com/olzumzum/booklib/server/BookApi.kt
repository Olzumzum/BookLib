package com.olzumzum.booklib.server

import com.olzumzum.bookslibrary.model.BooksResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BookApi {
    @GET("api/get?key=/b/OL1001932M")
    fun getAllBook(): Single<Response<BooksResponse>>
}