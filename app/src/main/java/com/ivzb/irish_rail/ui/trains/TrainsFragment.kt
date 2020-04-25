package com.ivzb.irish_rail.ui.trains

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ivzb.irish_rail.databinding.FragmentTrainsBinding
import com.ivzb.irish_rail.ui.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TrainsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var trainsViewModel: TrainsViewModel
    private lateinit var binding: FragmentTrainsBinding

    private var adapter: ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        trainsViewModel = ViewModelProvider(this, viewModelFactory).get(TrainsViewModel::class.java)

        binding = FragmentTrainsBinding.inflate(inflater, container, false).apply {
            viewModel = trainsViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        trainsViewModel.trains.observe(viewLifecycleOwner, Observer {
            showTrains(binding.rvTrains, it)
        })

        return binding.root
    }

    private fun showTrains(recyclerView: RecyclerView, list: List<Any>?) {
        if (adapter == null) {
            val trainsViewBinder = TrainViewBinder(this, trainsViewModel)
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
}
