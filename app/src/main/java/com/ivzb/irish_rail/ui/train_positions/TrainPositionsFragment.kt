package com.ivzb.irish_rail.ui.train_positions

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.FragmentTrainPositionsBinding
import com.ivzb.irish_rail.domain.EventObserver
import com.ivzb.irish_rail.model.ui.train.TrainPosition
import com.ivzb.irish_rail.ui.*
import com.ivzb.irish_rail.ui.train_positions.TrainPositionsFragmentDirections.Companion.toTrainMovements
import com.ivzb.irish_rail.util.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TrainPositionsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var trainsViewModel: TrainPositionsViewModel
    private lateinit var binding: FragmentTrainPositionsBinding

    private var adapter: ItemAdapter? = null
    private var searchItem: MenuItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        trainsViewModel = provideViewModel(viewModelFactory)

        binding = FragmentTrainPositionsBinding.inflate(inflater, container, false).apply {
            viewModel = trainsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        trainsViewModel.trains.observe(viewLifecycleOwner, Observer {
            showTrains(binding.rvTrains, it)
        })

        trainsViewModel.trainClick.observe(viewLifecycleOwner, EventObserver {
            navigateToTrainMovements(it)
            closeSearch(searchItem)
            clearSearch(searchItem)
        })

        trainsViewModel.searchQuery.observe(viewLifecycleOwner, Observer {
            filterTrains(it)
        })

        setHasOptionsMenu(true)
        updateTitle(R.string.title_trains)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)

        createSearchMenu(menu, menuInflater, object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                trainsViewModel.search(query)
                updateTitle(R.string.title_trains, query)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                closeSearch(searchItem)
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
        val trainsViewBinder = TrainPositionsViewBinder(this, trainsViewModel)
        val emptyViewBinder = EmptyViewBinder()
        val noConnectionViewBinder = NoConnectionViewBinder()
        val viewBinders = HashMap<ItemClass, ItemBinder>().apply {
            put(trainsViewBinder.modelClass, trainsViewBinder as ItemBinder)
            put(emptyViewBinder.modelClass, emptyViewBinder as ItemBinder)
            put(noConnectionViewBinder.modelClass, noConnectionViewBinder as ItemBinder)
        }
        val queryMatcher = TrainPositionQueryMatcher()

        return ItemAdapter(viewBinders, queryMatcher)
    }

    private fun navigateToTrainMovements(trainPosition: TrainPosition) =
        findNavController().navigate(
            toTrainMovements(
                trainPosition.code,
                trainPosition.direction
            )
        )

    private fun filterTrains(query: String) {
        adapter?.filter?.filter(query)
    }
}
