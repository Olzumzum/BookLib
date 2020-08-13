package com.olzumzum.booklib.server

import android.util.Log
import com.olzumzum.bookslibrary.model.BooksResponse
import io.reactivex.Single
import retrofit2.Response


class BookServerCommunicator(val mBookApi: BookApi) {

    fun getAllBook(): Single<Response<BooksResponse>> {
      return mBookApi.getAllBook()

    }
}