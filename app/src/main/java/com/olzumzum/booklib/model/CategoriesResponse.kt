package com.olzumzum.booklib.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val categories: List<Category>,
    @SerializedName("status")
    val status: String
)