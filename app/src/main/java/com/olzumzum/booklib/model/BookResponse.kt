package com.olzumzum.booklib.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val books: List<Book>,
    @SerializedName("status")
    val status: String
)