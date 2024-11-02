package com.example.eventdicoding.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_event")
@Parcelize
data class FavoriteEvent(
    @field:PrimaryKey
    val id: String,

    @field:ColumnInfo(name = "name")
    var name: String = "",

    @field:ColumnInfo(name = "mediaCover")
    var mediaCover: String? = null,

    @field:ColumnInfo(name = "bookmarked")
    var isBookmarked: Boolean,

    @field:SerializedName("registrants")
    val registrants: Int? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("ownerName")
    val ownerName: String? = null,

    @field:SerializedName("cityName")
    val cityName: String? = null,

    @field:SerializedName("quota")
    val quota: Int? = null,

    @field:SerializedName("beginTime")
    val beginTime: String? = null,

    @field:SerializedName("endTime")
    val endTime: String? = null

) : Parcelable