package com.example.eventdicoding.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    var isBookmarked: Boolean
) : Parcelable