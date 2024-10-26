package com.example.eventdicoding.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.eventdicoding.data.response.ListEventsItem

class EventListAdapter : ListAdapter<ListEventsItem, EventViewHolder>(EventDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.titleTextView.text = event.name
        Glide.with(holder.imageView.context)
            .load(event.mediaCover)
            .into(holder.imageView)
        holder.bind(event)
    }
}