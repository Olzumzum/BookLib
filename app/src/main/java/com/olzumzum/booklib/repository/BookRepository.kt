package com.olzumzum.booklib.repository

import com.olzumzum.booklib.model.Book
import com.olzumzum.booklib.server.BookServerCommunicator
import io.reactivex.Single

class BookRepository(
    val bookServerCommunicator: BookServerCommunicator
) {
    fun getAllBook(): Single<List<Book>> = bookServerCommunicator.getAllBook()

    fun getBooksByDate(): Single<Int> = bookServerCommunicator.getBooksByDate()

}