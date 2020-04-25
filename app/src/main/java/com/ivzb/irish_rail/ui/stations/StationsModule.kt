package com.ivzb.irish_rail.ui.stations

import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class StationsModule {

    /**
     * Generates an [AndroidInjector] for the [StationsFragment] as a Dagger subcomponent of the
     * [MainModule].
     */
    @ContributesAndroidInjector
    internal abstract fun provideStationsFragment(): StationsFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [StationsViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(StationsViewModel::class)
    abstract fun bindStationsFragmentViewModel(viewModel: StationsViewModel): ViewModel
}
