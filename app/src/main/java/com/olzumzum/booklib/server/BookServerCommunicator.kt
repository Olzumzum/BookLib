package com.olzumzum.booklib.server

import com.olzumzum.booklib.model.Book
import io.reactivex.Single


class BookServerCommunicator(val mBookApi: BookApi) {


    fun getAllBook(): Single<List<Book>> {
        return mBookApi.getAllBooks()
            .flatMap { response ->
                Single.just(response.books)
            }
    }

    fun getBooksByDate(): Single<Int> {
        return mBookApi.getBooksByDate()
            .flatMap {
                    response ->
                Single.just(response.num_results)
            }
    }
}