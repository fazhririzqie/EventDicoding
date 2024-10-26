package com.example.eventdicoding.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventdicoding.R
import com.example.eventdicoding.data.response.ListEventsItem

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.tvPicture)
    val titleTextView: TextView = view.findViewById(R.id.tvTitle)

    fun bind(event: ListEventsItem) {
        itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("EVENT_ID", event.id.toString())
                putExtra("EVENT_NAME", event.name)
                putExtra("EVENT_MEDIA_COVER", event.mediaCover)
                putExtra("EVENT_OWNER_NAME", event.ownerName)
                putExtra("EVENT_BEGIN_TIME", event.beginTime)
                putExtra("EVENT_QUOTA", event.quota.toString())
                putExtra("EVENT_DESCRIPTION", event.description)
                putExtra("EVENT_LINK", event.link)
                putExtra("EVENT_REGISTRANTS", event.registrants.toString())
                // Tambahkan data lain yang ingin dikirim
            }
            context.startActivity(intent)
        }
    }

    companion object {
        fun create(parent: ViewGroup): EventViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
            return EventViewHolder(view)
        }
    }
}
