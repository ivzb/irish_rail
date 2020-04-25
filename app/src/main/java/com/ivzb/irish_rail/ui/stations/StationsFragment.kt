package com.ivzb.irish_rail.ui.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.FragmentStationsBinding
import com.ivzb.irish_rail.domain.EventObserver
import com.ivzb.irish_rail.model.Station
import com.ivzb.irish_rail.ui.EmptyViewBinder
import com.ivzb.irish_rail.ui.ItemAdapter
import com.ivzb.irish_rail.ui.ItemBinder
import com.ivzb.irish_rail.ui.ItemClass
import com.ivzb.irish_rail.ui.stations.StationsFragmentDirections.Companion.toStationDetails
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StationsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var stationsViewModel: StationsViewModel
    private lateinit var binding: FragmentStationsBinding

    private var adapter: ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        stationsViewModel =
            ViewModelProvider(this, viewModelFactory).get(StationsViewModel::class.java)

        binding = FragmentStationsBinding.inflate(inflater, container, false).apply {
            viewModel = stationsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        stationsViewModel.stations.observe(viewLifecycleOwner, Observer {
            showStations(binding.rvStations, it)
        })

        stationsViewModel.stationClick.observe(viewLifecycleOwner, EventObserver { station ->
            navigateToStationDetails(station)
        })

        requireActivity().title = getString(R.string.title_stations)

        return binding.root
    }

    private fun showStations(recyclerView: RecyclerView, list: List<Any>?) {
        if (adapter == null) {
            val stationsViewBinder = StationViewBinder(this, stationsViewModel)
            val emptyViewBinder = EmptyViewBinder()

            val viewBinders = HashMap<ItemClass, ItemBinder>().apply {
                put(
                    stationsViewBinder.modelClass,
                    stationsViewBinder as ItemBinder
                )

                put(
                    emptyViewBinder.modelClass,
                    emptyViewBinder as ItemBinder
                )
            }

            adapter = ItemAdapter(viewBinders)
        }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        }

        (recyclerView.adapter as ItemAdapter).submitList(list ?: emptyList())
    }

    private fun navigateToStationDetails(station: Station) =
        findNavController().navigate(
            toStationDetails(
                station.code,
                station.name
            )
        )
}
