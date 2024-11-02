package com.example.eventdicoding.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.eventdicoding.data.entity.FavoriteEvent

@Dao
interface EventDao {
    @Query("SELECT * FROM favorite_event ORDER BY id ASC")
    fun getFavoriteEvents(): LiveData<List<FavoriteEvent>>

    @Query("SELECT * FROM favorite_event where bookmarked = 1")
    fun getBookmarkedNews(): LiveData<List<FavoriteEvent>>

    @Insert
    suspend fun insertEvent(news: List<FavoriteEvent>)

    @Query("SELECT * FROM favorite_event WHERE id = :id")
    fun getFavoriteEventById(id: String): LiveData<FavoriteEvent>

    @Query("DELETE FROM favorite_event WHERE id = :id")
    suspend fun deleteFavoriteEventById(id: String)

    @Query("SELECT * FROM favorite_event")
    fun getAllFavoriteEvents(): LiveData<List<FavoriteEvent>>

}