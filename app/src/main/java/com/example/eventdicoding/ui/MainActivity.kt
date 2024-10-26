package com.example.eventdicoding.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventdicoding.data.response.EventResponse
import com.example.eventdicoding.data.retrofit.ApiConfig
import com.example.eventdicoding.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = EventListAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fetchEvents()
    }

    private fun fetchEvents() {
        binding.progressBar.visibility = View.VISIBLE
        val apiService = ApiConfig.getApiService()
        apiService.getEvents(1).enqueue(object : Callback<EventResponse> { //
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val events = response.body()?.listEvents ?: emptyList()
                    adapter.submitList(events)
                } else {
                    Log.e("MainActivity", "Failed to fetch events: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }
}