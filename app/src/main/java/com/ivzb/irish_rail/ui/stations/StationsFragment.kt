package com.ivzb.irish_rail.ui.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ivzb.irish_rail.databinding.FragmentStationsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StationsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var stationsViewModel: StationsViewModel
    private lateinit var binding: FragmentStationsBinding

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

        return binding.root
    }
}
