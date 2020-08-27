package com.olzumzum.booklib.model.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.model.pojo.InfoBook

data class InfoWithBooks(
    @Embedded
    val info: InfoBook,
    @Relation(parentColumn = "id", entityColumn = "id_info")
    val books: List<BookX>
)