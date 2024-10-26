package com.example.eventdicoding.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventdicoding.data.entity.FavoriteEvent
import com.example.eventdicoding.databinding.ItemEventBinding

class FavoriteEventAdapter(private val onItemClickListener: OnItemClickListener) : ListAdapter<FavoriteEvent, FavoriteEventAdapter.FavoriteEventViewHolder>(FavoriteEventDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(favoriteEvent: FavoriteEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteEventViewHolder, position: Int) {
        val favoriteEvent = getItem(position)
        holder.bind(favoriteEvent)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(favoriteEvent)
        }
    }

    class FavoriteEventViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteEvent: FavoriteEvent) {
            Glide.with(binding.tvPicture.context)
                .load(favoriteEvent.mediaCover)
                .into(binding.tvPicture)
            binding.tvTitle.text = favoriteEvent.name
        }
    }

    class FavoriteEventDiffCallback : DiffUtil.ItemCallback<FavoriteEvent>() {
        override fun areItemsTheSame(oldItem: FavoriteEvent, newItem: FavoriteEvent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteEvent, newItem: FavoriteEvent): Boolean {
            return oldItem == newItem
        }
    }
}