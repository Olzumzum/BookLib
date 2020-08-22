package com.olzumzum.booklib.model

import androidx.room.Embedded
import androidx.room.Relation

data class InfoWithBooks(
    @Embedded
    val info: InfoBooksByDate,
    @Relation(parentColumn = "id", entityColumn = "id_info")
    val books: List<BookX>
)