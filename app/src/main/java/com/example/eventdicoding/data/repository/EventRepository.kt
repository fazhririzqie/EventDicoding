package com.example.eventdicoding.data.repository

import androidx.lifecycle.LiveData
import com.example.eventdicoding.data.entity.FavoriteEvent
import com.example.eventdicoding.data.room.EventDao

class EventRepository(private val eventDao: EventDao) {

    val allFavoriteEvents: LiveData<List<FavoriteEvent>> = eventDao.getAllFavoriteEvents()

    suspend fun insert(events: List<FavoriteEvent>) {
        eventDao.insertEvent(events)
    }

    fun getFavoriteEventById(id: String): LiveData<FavoriteEvent> {
        return eventDao.getFavoriteEventById(id)
    }

    suspend fun deleteFavoriteEventById(id: String) {
        eventDao.deleteFavoriteEventById(id)
    }
}