package com.ivzb.irish_rail.ui.trains

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ivzb.irish_rail.databinding.FragmentTrainsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TrainsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var trainsViewModel: TrainsViewModel
    private lateinit var binding: FragmentTrainsBinding

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

        return binding.root
    }
}
