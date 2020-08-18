package com.olzumzum.booklib.repository

import com.olzumzum.booklib.model.Category
import com.olzumzum.booklib.model.InfoBooksByDate
import com.olzumzum.booklib.server.BookServerCommunicator
import io.reactivex.Single

class BookRepository(
    val bookServerCommunicator: BookServerCommunicator
) {
    fun getAllBook(): Single<List<Category>> = bookServerCommunicator.getAllBook()

    fun getBooksByDate(): Single<InfoBooksByDate> = bookServerCommunicator.getBooksByDate()

}