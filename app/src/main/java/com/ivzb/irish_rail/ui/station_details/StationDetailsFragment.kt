package com.ivzb.irish_rail.ui.station_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.databinding.FragmentStationDetailsBinding
import com.ivzb.irish_rail.domain.EventObserver
import com.ivzb.irish_rail.model.ui.station.StationDetails
import com.ivzb.irish_rail.ui.*
import com.ivzb.irish_rail.ui.station_details.StationDetailsFragmentDirections.Companion.toTrainMovements
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StationDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var stationDetailsViewModel: StationDetailsViewModel
    private lateinit var binding: FragmentStationDetailsBinding

    private var adapter: ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        stationDetailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(StationDetailsViewModel::class.java)

        binding = FragmentStationDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = stationDetailsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        stationDetailsViewModel.stationDetails.observe(viewLifecycleOwner, Observer {
            showStationDetails(binding.rvStationDetails, it)
        })

        stationDetailsViewModel.stationDetailsClick.observe(
            viewLifecycleOwner,
            EventObserver { stationDetails ->
                navigateToTrainMovements(stationDetails)
            })

        requireArguments().apply {
            val (stationCode, stationName) = StationDetailsFragmentArgs.fromBundle(this)
            binding.stationCode = stationCode
            stationDetailsViewModel.fetchStationDetails(stationCode)

            requireActivity().title = "Station $stationName ($stationCode)"
        }

        return binding.root
    }

    private fun showStationDetails(recyclerView: RecyclerView, list: List<Any>?) {
        if (adapter == null) {
            val stationDetailsViewBinder = StationDetailsViewBinder(this, stationDetailsViewModel)
            val emptyViewBinder = EmptyViewBinder()

            val viewBinders = HashMap<ItemClass, ItemBinder>().apply {
                put(
                    stationDetailsViewBinder.modelClass,
                    stationDetailsViewBinder as ItemBinder
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

    private fun navigateToTrainMovements(stationDetails: StationDetails) =
        findNavController().navigate(
            toTrainMovements(
                stationDetails.trainCode,
                stationDetails.direction
            )
        )
}
