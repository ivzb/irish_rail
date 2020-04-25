package com.ivzb.irish_rail.ui.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.databinding.FragmentStationsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StationsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var stationsViewModel: StationsViewModel
    private lateinit var binding: FragmentStationsBinding

    private var adapter: StationsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        stationsViewModel = ViewModelProvider(this, viewModelFactory).get(StationsViewModel::class.java)

        binding = FragmentStationsBinding.inflate(inflater, container, false).apply {
            viewModel = stationsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        stationsViewModel.stations.observe(viewLifecycleOwner, Observer {
            showStations(binding.rvStations, it)
        })

        return binding.root
    }

    private fun showStations(recyclerView: RecyclerView, list: List<Any>?) {
        if (adapter == null) {
            val stationsViewBinder = StationViewBinder(this, stationsViewModel)
            val emptyStationViewBinder = EmptyStationViewBinder()

            val viewBinders = HashMap<StationItemClass, StationItemBinder>().apply {
                put(
                    stationsViewBinder.modelClass,
                    stationsViewBinder as StationItemBinder
                )

                put(
                    emptyStationViewBinder.modelClass,
                    emptyStationViewBinder as StationItemBinder
                )
            }

            adapter = StationsAdapter(viewBinders)
        }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        }

        (recyclerView.adapter as StationsAdapter).submitList(list ?: emptyList())
    }
}
