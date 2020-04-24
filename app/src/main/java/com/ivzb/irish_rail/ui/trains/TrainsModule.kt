package com.ivzb.irish_rail.ui.trains

import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class TrainsModule {

    /**
     * Generates an [AndroidInjector] for the [TrainsFragment] as a Dagger subcomponent of the
     * [MainModule].
     */
    @ContributesAndroidInjector
    internal abstract fun provideTrainsFragment(): TrainsFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [TrainsViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(TrainsViewModel::class)
    abstract fun bindTrainsFragmentViewModel(viewModel: TrainsViewModel): ViewModel
}
