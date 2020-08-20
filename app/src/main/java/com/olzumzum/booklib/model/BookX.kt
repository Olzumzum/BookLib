package com.olzumzum.booklib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookX")
data class BookX(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("age_group")
    @ColumnInfo(name = "age_group")
    val ageGroup: String,
    @SerializedName("article_chapter_link")
    @ColumnInfo(name = "article_chapter_link")
    val articleChapterLink: String,
    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String,
    @SerializedName("book_image")
    @ColumnInfo(name = "book_image")
    val bookImage: String,
    @SerializedName("book_image_height")
    @ColumnInfo(name = "book_image_height")
    val bookImageHeight: Int,
    @SerializedName("book_image_width")
    @ColumnInfo(name = "book_image_width")
    val bookImageWidth: Int,
    @SerializedName("book_review_link")
    @ColumnInfo(name = "book_review_link")
    val bookReviewLink: String,
    @SerializedName("book_uri")
    @ColumnInfo(name = "book_uri")
    val bookUri: String,
    @SerializedName("contributor")
    @ColumnInfo(name = "contributor")
    val contributor: String,
    @SerializedName("contributor_note")
    @ColumnInfo(name = "contributor_note")
    val contributorNote: String,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String,
    @SerializedName("first_chapter_link")
    @ColumnInfo(name = "first_chapter_link")
    val firstChapterLink: String,
    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: Int,
    @SerializedName("publisher")
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @SerializedName("rank")
    @ColumnInfo(name = "rank")
    val rank: Int,
    @SerializedName("rank_last_week")
    @ColumnInfo(name = "rank_last_week")
    val rankLastWeek: Int,
    @SerializedName("sunday_review_link")
    @ColumnInfo(name = "sunday_review_link")
    val sundayReviewLink: String,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("weeks_on_list")
    @ColumnInfo(name = "weeks_on_list")
    val weeksOnList: Int
)