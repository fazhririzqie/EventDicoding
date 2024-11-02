package com.example.eventdicoding.ui.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventdicoding.data.entity.FavoriteEvent
import com.example.eventdicoding.databinding.FragmentFavoriteBinding
import com.example.eventdicoding.ui.DetailActivity
import com.example.eventdicoding.ui.FavoriteEventAdapter
import com.example.eventdicoding.ui.EventViewModel

class FavoriteFragment : Fragment(), FavoriteEventAdapter.OnItemClickListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val eventViewModel: EventViewModel by viewModels()
    private lateinit var adapter: FavoriteEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteEventAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE

        eventViewModel.allFavoriteEvents.observe(viewLifecycleOwner) { favoriteEvents ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(favoriteEvents)
        }
    }

    override fun onItemClick(favoriteEvent: FavoriteEvent) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra("EVENT_ID", favoriteEvent.id)
            putExtra("EVENT_NAME", favoriteEvent.name)
            putExtra("EVENT_MEDIA_COVER", favoriteEvent.mediaCover)
            putExtra("EVENT_OWNER_NAME", favoriteEvent.ownerName)
            putExtra("EVENT_BEGIN_TIME", favoriteEvent.beginTime)
            putExtra("EVENT_QUOTA", favoriteEvent.quota?.toString())
            putExtra("EVENT_DESCRIPTION", favoriteEvent.description)
            putExtra("EVENT_LINK", favoriteEvent.link)
            putExtra("EVENT_REGISTRANTS", favoriteEvent.registrants?.toString())



        }
        startActivity(intent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}