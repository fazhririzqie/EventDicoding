package com.example.eventdicoding.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.eventdicoding.data.entity.FavoriteEvent
import com.example.eventdicoding.data.repository.EventRepository
import com.example.eventdicoding.data.room.EventDatabase
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventRepository
    val allFavoriteEvents: LiveData<List<FavoriteEvent>>

    init {
        val eventDao = EventDatabase.getInstance(application).eventDao()
        repository = EventRepository(eventDao)
        allFavoriteEvents = repository.allFavoriteEvents
    }

    fun insert(events: List<FavoriteEvent>) = viewModelScope.launch {
        repository.insert(events)
    }

    fun getFavoriteEventById(id: String): LiveData<FavoriteEvent> {
        return repository.getFavoriteEventById(id)
    }

    fun deleteFavoriteEventById(id: String) = viewModelScope.launch {
        repository.deleteFavoriteEventById(id)
    }
}