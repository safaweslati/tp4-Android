package com.tp.tp4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tp.tp4.databinding.FragmentDetailsBinding
import com.tp.tp4.viewmodels.BusScheduleViewModel
import com.tp.tp4.viewmodels.BusScheduleViewModelFactory

class DetailsFragment : Fragment() {

    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (requireActivity().application as BusScheduleApplication).database.ScheduleDao()
        )
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        busStopAdapter = BusStopAdapter()
        recyclerView.adapter = busStopAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stopName = arguments?.getString("stopName")

        viewModel.scheduleForStopName(stopName.orEmpty()).observe(viewLifecycleOwner) {
            busStopAdapter.updateList(it)
        }
    }
}
