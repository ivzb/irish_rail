package com.ivzb.irish_rail.di

import com.ivzb.irish_rail.ui.launcher.LauncherActivity
import com.ivzb.irish_rail.ui.launcher.LauncherModule
import com.ivzb.irish_rail.ui.main.MainActivity
import com.ivzb.irish_rail.ui.main.MainModule
import com.ivzb.irish_rail.ui.station_details.StationDetailsModule
import com.ivzb.irish_rail.ui.stations.StationsModule
import com.ivzb.irish_rail.ui.train_movements.TrainMovementsModule
import com.ivzb.irish_rail.ui.train_positions.TrainPositionsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LauncherModule::class])
    internal abstract fun launcherActivity(): LauncherActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            TrainPositionsModule::class,
            TrainMovementsModule::class,
            StationsModule::class,
            StationDetailsModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}
