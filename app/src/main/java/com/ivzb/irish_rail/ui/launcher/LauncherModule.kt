package com.ivzb.irish_rail.ui.launcher

import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class LauncherModule {

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [LauncherViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindLauncherViewModel(viewModel: LauncherViewModel): ViewModel
}
