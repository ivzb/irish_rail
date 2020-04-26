package com.ivzb.irish_rail.ui.launcher

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ivzb.irish_rail.ui.main.MainActivity
import com.ivzb.irish_rail.util.provideViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * A 'Trampoline' activity for sending users to main screen on launch.
 */
class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: LauncherViewModel = provideViewModel(viewModelFactory)

        MainActivity.start(this)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
