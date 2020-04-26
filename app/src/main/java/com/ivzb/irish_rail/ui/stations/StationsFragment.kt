package com.ivzb.irish_rail.ui.stations

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.FragmentStationsBinding
import com.ivzb.irish_rail.domain.EventObserver
import com.ivzb.irish_rail.model.ui.station.Station
import com.ivzb.irish_rail.ui.*
import com.ivzb.irish_rail.ui.stations.StationsFragmentDirections.Companion.toStationDetails
import com.ivzb.irish_rail.util.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StationsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var stationsViewModel: StationsViewModel
    private lateinit var binding: FragmentStationsBinding

    private var adapter: ItemAdapter? = null
    private var searchItem: MenuItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        stationsViewModel = provideViewModel(viewModelFactory)

        binding = FragmentStationsBinding.inflate(inflater, container, false).apply {
            viewModel = stationsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        stationsViewModel.stations.observe(viewLifecycleOwner, Observer {
            showStations(binding.rvStations, it)
        })

        stationsViewModel.stationClick.observe(viewLifecycleOwner, EventObserver {
            navigateToStationDetails(it)
            closeSearch(searchItem)
            clearSearch(searchItem)
        })

        stationsViewModel.searchQuery.observe(viewLifecycleOwner, Observer {
            filterStations(it)
        })

        setHasOptionsMenu(true)
        updateTitle(R.string.title_stations)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)

        createSearchMenu(menu, menuInflater, object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                stationsViewModel.search(query)
                updateTitle(R.string.title_stations, query)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                closeSearch(searchItem)
                return true
            }
        })

        searchItem = menu.findItem(R.id.search)
    }

    private fun showStations(recyclerView: RecyclerView, list: List<Any>) {
        if (adapter == null) {
            adapter = createAdapter()
        }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        }

        (recyclerView.adapter as ItemAdapter).setList(list)
    }

    private fun createAdapter(): ItemAdapter {
        val stationsViewBinder = StationViewBinder(this, stationsViewModel)
        val emptyViewBinder = EmptyViewBinder()
        val viewBinders = HashMap<ItemClass, ItemBinder>().apply {
            put(stationsViewBinder.modelClass, stationsViewBinder as ItemBinder)
            put(emptyViewBinder.modelClass, emptyViewBinder as ItemBinder)
        }
        val queryMatcher = StationQueryMatcher()

        return ItemAdapter(viewBinders, queryMatcher)
    }

    private fun navigateToStationDetails(station: Station) =
        findNavController().navigate(
            toStationDetails(
                station.code,
                station.name
            )
        )

    private fun filterStations(query: String) {
        adapter?.filter?.filter(query)
    }
}
