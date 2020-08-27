package com.olzumzum.booklib.model.dto

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.olzumzum.booklib.model.pojo.BookX
import java.io.Serializable

/**
 * информация о книгах-бестселлерах
 * по указанной дате, приходящая с сервера
 */
data class InfoBooksByDate (
    @SerializedName("bestsellers_date")
    val bestsellersDate: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("next_published_date")
    val nextPublishedDate: String,
    @SerializedName("normal_list_ends_at")
    val normalListEndsAt: Int,
    @SerializedName("previous_published_date")
    val previousPublishedDate: String,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("published_date_description")
    val publishedDateDescription: String,
    @SerializedName("updated")
    val updated: String
)