package com.olzumzum.booklib.server

import android.util.Log
import com.olzumzum.bookslibrary.model.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookServerCommunicator(val mBookApi: BookApi) {

    fun getAllBook(){

      val m = mBookApi.getAllBook()
     Log.e("MyLog", "Response $m")
        m.enqueue(object: Callback<BookResponse> {
            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Log.e("MyLog", "Error loading")
            }

            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                val k = response.body()
                Log.e("MyLog", "Response ${k?.status}")
            }
        })
    }
}