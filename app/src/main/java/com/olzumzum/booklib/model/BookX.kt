package com.olzumzum.booklib.model

import com.google.gson.annotations.SerializedName

data class BookX(
    @SerializedName("age_group")
    val age_group: String,
    @SerializedName("article_chapter_link")
    val article_chapter_link: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("book_image")
    val book_image: String,
    @SerializedName("book_image_height")
    val book_image_height: Int,
    @SerializedName("book_image_width")
    val book_image_width: Int,
    @SerializedName("book_review_link")
    val book_review_link: String,
    @SerializedName("book_uri")
    val book_uri: String,
    @SerializedName("contributor")
    val contributor: String,
    @SerializedName("contributor_note")
    val contributor_note: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("first_chapter_link")
    val first_chapter_link: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("rank_last_week")
    val rank_last_week: Int,
    @SerializedName("sunday_review_link")
    val sunday_review_link: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("weeks_on_list")
    val weeks_on_list: Int
)