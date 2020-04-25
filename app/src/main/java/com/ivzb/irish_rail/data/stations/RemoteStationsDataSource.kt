package com.ivzb.irish_rail.data.stations

import com.ivzb.irish_rail.model.Station
import com.ivzb.irish_rail.util.NetworkUtils
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Downloads and parses stations data.
 */
class RemoteStationsDataSource @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val retrofit: Retrofit
) : StationsDataSource {

    override fun fetchStations(): List<Station>? {
        if (!networkUtils.hasNetworkConnection()) {
            return null
        }

        val response = retrofit.create<StationsAPI>(StationsAPI::class.java).fetchStations().execute()

        return response.body()?.stations?.map {
            it.asStation()
        }
    }
}
