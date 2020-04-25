package com.ivzb.irish_rail.ui.train_positions

import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class TrainPositionsModule {

    /**
     * Generates an [AndroidInjector] for the [TrainPositionsFragment] as a Dagger subcomponent of the
     * [MainModule].
     */
    @ContributesAndroidInjector
    internal abstract fun provideTrainsFragment(): TrainPositionsFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [TrainPositionsViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(TrainPositionsViewModel::class)
    abstract fun bindTrainsFragmentViewModel(viewModel: TrainPositionsViewModel): ViewModel
}
