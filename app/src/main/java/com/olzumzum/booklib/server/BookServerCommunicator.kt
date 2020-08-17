package com.olzumzum.booklib.server

import com.olzumzum.booklib.model.Category
import com.olzumzum.booklib.model.InfoByDateBook
import io.reactivex.Single


class BookServerCommunicator(val mBookApi: BookApi) {


    fun getAllBook(): Single<List<Category>> {
        return mBookApi.getAllBooks()
            .flatMap { response ->
                Single.just(response.categories)
            }
    }

    fun getBooksByDate(): Single<InfoByDateBook> {
        return mBookApi.getBooksByDate()
            .flatMap { response ->
                Single.just(response.infoByDateBook)
            }
    }
}