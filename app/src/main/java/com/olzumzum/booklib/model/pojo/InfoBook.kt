package com.olzumzum.booklib.model.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(tableName = "info_books_by_date")
data class InfoBook(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("bestsellers_date")
    @ColumnInfo(name = "bestsellers_date")
    val bestsellersDate: String,
    @SerializedName("display_name")
    @ColumnInfo(name = "display_name")
    val displayName: String,
    @SerializedName("next_published_date")
    @ColumnInfo(name = "next_published_date")
    val nextPublishedDate: String,
    @SerializedName("normal_list_ends_at")
    @ColumnInfo(name = "normal_list_ends_at")
    val normalListEndsAt: Int,
    @SerializedName("previous_published_date")
    @ColumnInfo(name = "previous_published_date")
    val previousPublishedDate: String,
    @SerializedName("published_date")
    @ColumnInfo(name = "published_date")
    val publishedDate: String,
    @SerializedName("published_date_description")
    @ColumnInfo(name = "published_date_description")
    val publishedDateDescription: String,
    @SerializedName("updated")
    @ColumnInfo(name = "updated")
    val updated: String
)