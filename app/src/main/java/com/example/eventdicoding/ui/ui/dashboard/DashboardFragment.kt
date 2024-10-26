package com.example.eventdicoding.ui.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventdicoding.R
import com.example.eventdicoding.data.response.EventResponse
import com.example.eventdicoding.data.retrofit.ApiConfig
import com.example.eventdicoding.ui.EventListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventListAdapter
    private lateinit var progressBar: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        adapter = EventListAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fetchEvents()
        return view
    }

    private fun fetchEvents() {
        progressBar.visibility = View.VISIBLE
        val apiService = ApiConfig.getApiService()
        apiService.getEvents(0).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val events = response.body()?.listEvents ?: emptyList()
                    adapter.submitList(events)
                } else {
                    Log.e("DashboardFragment", "Failed to fetch events: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e("DashboardFragment", "Error: ${t.message}")
            }
        })
    }
}