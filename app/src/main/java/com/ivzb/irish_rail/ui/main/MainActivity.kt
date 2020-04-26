package com.ivzb.irish_rail.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ivzb.irish_rail.R
import com.ivzb.irish_rail.util.provideViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel(viewModelFactory)

        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
