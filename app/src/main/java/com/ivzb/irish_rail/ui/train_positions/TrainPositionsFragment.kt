package com.ivzb.irish_rail.ui.train_positions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.databinding.FragmentTrainPositionsBinding
import com.ivzb.irish_rail.domain.EventObserver
import com.ivzb.irish_rail.model.TrainPosition
import com.ivzb.irish_rail.ui.*
import com.ivzb.irish_rail.ui.train_positions.TrainPositionsFragmentDirections.Companion.toTrainMovements
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TrainPositionsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var trainsViewModel: TrainPositionsViewModel
    private lateinit var binding: FragmentTrainPositionsBinding

    private var adapter: ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        trainsViewModel =
            ViewModelProvider(this, viewModelFactory).get(TrainPositionsViewModel::class.java)

        binding = FragmentTrainPositionsBinding.inflate(inflater, container, false).apply {
            viewModel = trainsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        trainsViewModel.trains.observe(viewLifecycleOwner, Observer {
            showTrains(binding.rvTrains, it)
        })

        trainsViewModel.trainClick.observe(viewLifecycleOwner, EventObserver { trainPosition ->
            navigateToTrainMovements(trainPosition)
        })

        requireActivity().title = getString(R.string.title_trains)

        return binding.root
    }

    private fun showTrains(recyclerView: RecyclerView, list: List<Any>?) {
        if (adapter == null) {
            val trainsViewBinder = TrainPositionsViewBinder(this, trainsViewModel)
            val emptyViewBinder = EmptyViewBinder()

            val viewBinders = HashMap<ItemClass, ItemBinder>().apply {
                put(
                    trainsViewBinder.modelClass,
                    trainsViewBinder as ItemBinder
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

    private fun navigateToTrainMovements(trainPosition: TrainPosition) =
        findNavController().navigate(
            toTrainMovements(
                trainPosition.code,
                trainPosition.date,
                trainPosition.direction
            )
        )

    private fun showError(@StringRes messageResId: Int) =
        Toast.makeText(requireContext(), messageResId, Toast.LENGTH_LONG).show()
}
