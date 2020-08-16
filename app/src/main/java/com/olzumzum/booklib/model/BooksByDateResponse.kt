package com.olzumzum.booklib.model

import com.google.gson.annotations.SerializedName

data class BooksByDateResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("last_modified")
    val last_modified: String,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val results: Results,
    @SerializedName("status")
    val status: String
)