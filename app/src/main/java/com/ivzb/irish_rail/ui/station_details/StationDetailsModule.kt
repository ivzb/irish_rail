package com.ivzb.irish_rail.ui.station_details

import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class StationDetailsModule {

    /**
     * Generates an [AndroidInjector] for the [StationDetailssFragment] as a Dagger subcomponent of the
     * [MainModule].
     */
    @ContributesAndroidInjector
    internal abstract fun provideStationDetailsFragment(): StationDetailsFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [StationDetailssViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(StationDetailsViewModel::class)
    abstract fun bindStationDetailsFragmentViewModel(viewModel: StationDetailsViewModel): ViewModel
}
