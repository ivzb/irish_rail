package com.ivzb.irish_rail.ui.train_movements

import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class TrainMovementsModule {

    /**
     * Generates an [AndroidInjector] for the [TrainMovementsFragment] as a Dagger subcomponent of the
     * [MainModule].
     */
    @ContributesAndroidInjector
    internal abstract fun provideTrainsFragment(): TrainMovementsFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [TrainMovementsViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(TrainMovementsViewModel::class)
    abstract fun bindTrainsFragmentViewModel(viewModel: TrainMovementsViewModel): ViewModel
}
