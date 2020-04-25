package com.ivzb.irish_rail.di

import com.ivzb.irish_rail.data.stations.RemoteStationsDataSource
import com.ivzb.irish_rail.data.stations.StationsDataSource
import com.ivzb.irish_rail.data.stations.StationsRepository
import com.ivzb.irish_rail.data.trains.RemoteTrainsDataSource
import com.ivzb.irish_rail.data.trains.TrainsDataSource
import com.ivzb.irish_rail.data.trains.TrainsRepository
import com.ivzb.irish_rail.util.NetworkUtils
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideTrainsRemoteDataSource(
        networkUtils: NetworkUtils,
        retrofit: Retrofit
    ): TrainsDataSource = RemoteTrainsDataSource(networkUtils, retrofit)

    @Singleton
    @Provides
    fun provideTrainsRepository(
        trainsDataSource: TrainsDataSource
    ): TrainsRepository = TrainsRepository(trainsDataSource)

    @Singleton
    @Provides
    fun provideStationsRemoteDataSource(
        networkUtils: NetworkUtils,
        retrofit: Retrofit
    ): StationsDataSource = RemoteStationsDataSource(networkUtils, retrofit)

    @Singleton
    @Provides
    fun provideStationsRepository(
        stationsDataSource: StationsDataSource
    ): StationsRepository = StationsRepository(stationsDataSource)

    @Singleton
    @Provides
    fun provideOkHttp(
    ): OkHttpClient = OkHttpClient.Builder()
        .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS))
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    companion object {
        private const val BASE_URL = "https://api.irishrail.ie/"
    }
}
