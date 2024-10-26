package com.example.eventdicoding.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.eventdicoding.R
import com.example.eventdicoding.data.entity.FavoriteEvent
import com.example.eventdicoding.databinding.ActivityDetailBinding
import com.example.eventdicoding.ui.viewmodel.EventViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventId = intent.getStringExtra("EVENT_ID")
        val eventName = intent.getStringExtra("EVENT_NAME")
        val eventMediaCover = intent.getStringExtra("EVENT_MEDIA_COVER")
        val eventOwnerName = intent.getStringExtra("EVENT_OWNER_NAME")
        val evenBeginTime = intent.getStringExtra("EVENT_BEGIN_TIME")
        val eventQuota = intent.getStringExtra("EVENT_QUOTA")?.toIntOrNull() ?: 0
        val eventDescription = intent.getStringExtra("EVENT_DESCRIPTION")
        val eventLink = intent.getStringExtra("EVENT_LINK")
        val eventRegistrants = intent.getStringExtra("EVENT_REGISTRANTS")?.toIntOrNull() ?: 0

        val remainingQuota = (eventQuota - eventRegistrants).coerceAtLeast(0)


        binding.tvBeginTime.text = evenBeginTime
        binding.tvTitle.text = eventName
        binding.tvOwnerName.text = eventOwnerName
        binding.tvQuota.text = remainingQuota.toString()
        binding.tvDescription.text = HtmlCompat.fromHtml(
            eventDescription.toString(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        Glide.with(this)
            .load(eventMediaCover)
            .into(binding.ivMediaCover)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eventLink))
            startActivity(intent)
        }

        eventId?.let { id ->
            eventViewModel.getFavoriteEventById(id).observe(this, Observer { favoriteEvent ->
                if (favoriteEvent == null) {
                    binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                } else {
                    binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
                }

                binding.fabFavorite.setOnClickListener {
                    if (favoriteEvent == null) {
                        val newFavoriteEvent = FavoriteEvent(
                            id = id,
                            name = eventName ?: "Unknown Event",
                            mediaCover = eventMediaCover ?: "",
                            isBookmarked = true
                        )
                        eventViewModel.insert(listOf(newFavoriteEvent))
                        binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
                    } else {
                        eventViewModel.deleteFavoriteEventById(id)
                        binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                    }
                }
            })
        }
    }
}