package com.ivzb.irish_rail.di

import com.ivzb.irish_rail.data.trains.RemoteTrainsDataSource
import com.ivzb.irish_rail.data.trains.TrainsDataSource
import com.ivzb.irish_rail.data.trains.TrainsRepository
import com.ivzb.irish_rail.util.NetworkUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideTrainsRemoteDataSource(
        networkUtils: NetworkUtils
    ): TrainsDataSource = RemoteTrainsDataSource(networkUtils)

    @Singleton
    @Provides
    fun provideTrainsRepository(
        trainsDataSource: TrainsDataSource
    ): TrainsRepository = TrainsRepository(trainsDataSource)
}
