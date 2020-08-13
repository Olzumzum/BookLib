package com.olzumzum.bookslibrary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "books_response")
data class BooksResponse(
//    @SerializedName("result")
//    @ColumnInfo(name = "result")
//    val result: Book,
    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String
)