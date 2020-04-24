package com.ivzb.irish_rail.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ivzb.irish_rail.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setContentView(R.layout.activity_main)
    }
}
