package com.olzumzum.bookslibrary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "books_response")
data class BookResponse(
    @SerializedName("result")
    val result: Book,
    @SerializedName("status")
    val status: String
)