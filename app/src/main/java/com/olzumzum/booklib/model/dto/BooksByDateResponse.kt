package com.olzumzum.booklib.model.dto

import com.google.gson.annotations.SerializedName
import com.olzumzum.booklib.model.pojo.InfoBook

data class BooksByDateResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("last_modified")
    val last_modified: String,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val infoBook: InfoBooksByDate,
    @SerializedName("status")
    val status: String
)