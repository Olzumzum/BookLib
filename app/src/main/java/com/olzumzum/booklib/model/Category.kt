package com.olzumzum.booklib.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("display_name")
    val display_name: String,
    @SerializedName("list_name")
    val list_name: String,
    @SerializedName("list_name_encoded")
    val list_name_encoded: String,
    @SerializedName("newest_published_date")
    val newest_published_date: String,
    @SerializedName("oldest_published_date")
    val oldest_published_date: String,
    @SerializedName("updated")
    val updated: String
)