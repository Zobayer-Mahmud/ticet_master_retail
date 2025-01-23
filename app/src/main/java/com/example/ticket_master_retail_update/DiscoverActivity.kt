//package com.example.ticket_master_retail_update
//
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.ticket_master_retail_update.api.ApiClient
//import com.example.ticket_master_retail_update.api.EventResponse
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class DiscoverActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_discover)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        fetchEvents()
//    }
//
//    private fun fetchEvents() {
//        val apiKey = "C1pkjWYZmFChCKF2NO0K0mOsD11pZHpA" // Replace with your actual API key
//        val apiClient = ApiClient.instance
//
//        apiClient.getEvents(apiKey, keyword = "music").enqueue(object : Callback<EventResponse> {
//            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
//                if (response.isSuccessful) {
//                    val events = response.body()?._embedded?.events
//                    events?.forEach { event ->
//                        Toast.makeText(
//                            this@DiscoverActivity,
//                            "Event: ${event.name} on ${event.dates.start.localDate}",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                } else {
//                    Toast.makeText(this@DiscoverActivity, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
//                Toast.makeText(this@DiscoverActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}

package com.example.ticket_master_retail_update

import EventAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticket_master_retail_update.api.ApiClient
import com.example.ticket_master_retail_update.api.Event
import com.example.ticket_master_retail_update.api.EventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_discover)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fetchEvents()
    }

    private fun fetchEvents() {
        val apiKey = "C1pkjWYZmFChCKF2NO0K0mOsD11pZHpA" // Replace with your actual API key
        val apiClient = ApiClient.instance

        apiClient.getEvents(apiKey,).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val events = response.body()?._embedded?.events
                    if (events != null) {
                        Log.d("events","$events");
                        Log.d("response","${response.body()}");
                        recyclerView.adapter = EventAdapter(events)
                    } else {
                        Toast.makeText(
                            this@DiscoverActivity,
                            "No events found",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(this@DiscoverActivity, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Toast.makeText(this@DiscoverActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}

