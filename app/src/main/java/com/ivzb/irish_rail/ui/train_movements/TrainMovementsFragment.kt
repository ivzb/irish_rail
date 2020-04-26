package com.ivzb.irish_rail.ui.train_movements

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.FragmentTrainMovementsBinding
import com.ivzb.irish_rail.domain.EventObserver
import com.ivzb.irish_rail.model.ui.train.TrainMovement
import com.ivzb.irish_rail.ui.*
import com.ivzb.irish_rail.ui.train_movements.TrainMovementsFragmentDirections.Companion.toStationDetails
import com.ivzb.irish_rail.util.capitalizeWords
import com.ivzb.irish_rail.util.createSearchMenu
import com.ivzb.irish_rail.util.provideViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TrainMovementsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var trainsViewModel: TrainMovementsViewModel
    private lateinit var binding: FragmentTrainMovementsBinding

    private var adapter: ItemAdapter? = null
    private var searchItem: MenuItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        trainsViewModel = provideViewModel(viewModelFactory)

        binding = FragmentTrainMovementsBinding.inflate(inflater, container, false).apply {
            viewModel = trainsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        trainsViewModel.trains.observe(viewLifecycleOwner, Observer {
            showTrains(binding.rvTrains, it)
        })

        trainsViewModel.trainClick.observe(viewLifecycleOwner, EventObserver {
            navigateToStationDetails(it)
            closeSearch()
        })

        trainsViewModel.searchQuery.observe(viewLifecycleOwner, Observer {
            filterTrains(it)
        })

        setHasOptionsMenu(true)
        requireArguments().apply {
            val (trainId, direction) = TrainMovementsFragmentArgs.fromBundle(this)
            binding.trainId = trainId
            trainsViewModel.fetchTrains(trainId)

            val formattedDirection = direction.toLowerCase().removePrefix("to ").capitalizeWords()
            requireActivity().title = "Train ${trainId.trim()} to $formattedDirection"
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)

        createSearchMenu(menu, menuInflater, object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                trainsViewModel.search(query)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                closeSearch()
                return true
            }
        })

        searchItem = menu.findItem(R.id.search)
    }

    private fun showTrains(recyclerView: RecyclerView, list: List<Any>) {
        if (adapter == null) {
            adapter = createAdapter()
        }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        }

        (recyclerView.adapter as ItemAdapter).setList(list)
    }

    private fun createAdapter(): ItemAdapter {
        val trainsViewBinder = TrainMovementsViewBinder(this, trainsViewModel)
        val emptyViewBinder = EmptyViewBinder()
        val viewBinders = HashMap<ItemClass, ItemBinder>().apply {
            put(trainsViewBinder.modelClass, trainsViewBinder as ItemBinder)
            put(emptyViewBinder.modelClass, emptyViewBinder as ItemBinder)
        }
        val queryMatcher = TrainMovementQueryMatcher()

        return ItemAdapter(viewBinders, queryMatcher)
    }

    private fun navigateToStationDetails(trainMovement: TrainMovement) =
        findNavController().navigate(
            toStationDetails(
                trainMovement.locationCode,
                trainMovement.locationName
            )
        )

    private fun filterTrains(query: String) {
        adapter?.filter?.filter(query)
    }

    private fun closeSearch() {
        searchItem?.collapseActionView()
    }
}
