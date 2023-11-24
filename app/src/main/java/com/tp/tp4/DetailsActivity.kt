package com.tp.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tp.tp4.databinding.ActivityMainBinding
import com.tp.tp4.viewmodels.BusScheduleViewModel
import com.tp.tp4.viewmodels.BusScheduleViewModelFactory

class DetailsActivity : AppCompatActivity() {
    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (application as BusScheduleApplication).database.ScheduleDao()
        )
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up RecyclerView
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val stopName = intent.getStringExtra("stopName")
        busStopAdapter = BusStopAdapter()
        recyclerView.adapter = busStopAdapter

        viewModel.scheduleForStopName(stopName.orEmpty()).observe(this) {
            busStopAdapter.updateList(it)
        }
    }
}
