package com.tp.tp4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tp.tp4.databinding.FragmentBusScheduleBinding
import com.tp.tp4.viewmodels.BusScheduleViewModel
import com.tp.tp4.viewmodels.BusScheduleViewModelFactory
import androidx.navigation.fragment.findNavController
import com.tp.tp4.database.entities.Schedule


class BusScheduleFragment : Fragment() {

    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (requireActivity().application as BusScheduleApplication).database.ScheduleDao()
        )
    }

    private lateinit var binding: FragmentBusScheduleBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bus_schedule, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            findNavController().navigate(BusScheduleFragmentDirections.actionMainFragmentToDetailsFragment(schedule.stop_name))
        }

        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(viewLifecycleOwner) { busStopAdapter.updateList(it) }

        return view
    }

}
