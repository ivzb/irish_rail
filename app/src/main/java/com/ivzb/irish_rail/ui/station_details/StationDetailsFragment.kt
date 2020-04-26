package com.ivzb.irish_rail.ui.station_details

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.FragmentStationDetailsBinding
import com.ivzb.irish_rail.domain.EventObserver
import com.ivzb.irish_rail.model.ui.station.StationDetails
import com.ivzb.irish_rail.ui.*
import com.ivzb.irish_rail.ui.station_details.StationDetailsFragmentDirections.Companion.toTrainMovements
import com.ivzb.irish_rail.util.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StationDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var stationDetailsViewModel: StationDetailsViewModel
    private lateinit var binding: FragmentStationDetailsBinding
    private lateinit var title: String

    private var adapter: ItemAdapter? = null
    private var searchItem: MenuItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        stationDetailsViewModel = provideViewModel(viewModelFactory)

        binding = FragmentStationDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = stationDetailsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        stationDetailsViewModel.stationDetails.observe(viewLifecycleOwner, Observer {
            showStationDetails(binding.rvStationDetails, it)
        })

        stationDetailsViewModel.stationDetailsClick.observe(viewLifecycleOwner, EventObserver {
            navigateToTrainMovements(it)
            closeSearch(searchItem)
            clearSearch(searchItem)
        })

        stationDetailsViewModel.searchQuery.observe(viewLifecycleOwner, Observer {
            filterStationDetails(it)
        })

        setHasOptionsMenu(true)
        requireArguments().apply {
            val (stationCode, stationName) = StationDetailsFragmentArgs.fromBundle(this)
            binding.stationCode = stationCode
            stationDetailsViewModel.fetchStationDetails(stationCode)

            title = "Station $stationName ($stationCode)"
            updateTitle(title)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)

        createSearchMenu(menu, menuInflater, object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                stationDetailsViewModel.search(query)
                updateTitle(title, query)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                closeSearch(searchItem)
                return true
            }
        })

        searchItem = menu.findItem(R.id.search)
    }

    private fun showStationDetails(recyclerView: RecyclerView, list: List<Any>) {
        if (adapter == null) {
            adapter = createAdapter()
        }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        }

        (recyclerView.adapter as ItemAdapter).setList(list)
    }

    private fun createAdapter(): ItemAdapter {
        val stationDetailsViewBinder = StationDetailsViewBinder(this, stationDetailsViewModel)
        val emptyViewBinder = EmptyViewBinder()
        val noConnectionViewBinder = NoConnectionViewBinder()
        val viewBinders = HashMap<ItemClass, ItemBinder>().apply {
            put(stationDetailsViewBinder.modelClass, stationDetailsViewBinder as ItemBinder)
            put(emptyViewBinder.modelClass, emptyViewBinder as ItemBinder)
            put(noConnectionViewBinder.modelClass, noConnectionViewBinder as ItemBinder)
        }
        val queryMatcher = StationDetailsQueryMatcher()

        return ItemAdapter(viewBinders, queryMatcher)
    }

    private fun navigateToTrainMovements(stationDetails: StationDetails) =
        findNavController().navigate(
            toTrainMovements(
                stationDetails.trainCode,
                stationDetails.direction
            )
        )

    private fun filterStationDetails(query: String) {
        adapter?.filter?.filter(query)
    }
}
