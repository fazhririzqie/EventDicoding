package com.example.eventdicoding.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.eventdicoding.data.response.ListEventsItem

class EventDiffCallback : DiffUtil.ItemCallback<ListEventsItem>() {
    override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
        return oldItem == newItem
    }
}