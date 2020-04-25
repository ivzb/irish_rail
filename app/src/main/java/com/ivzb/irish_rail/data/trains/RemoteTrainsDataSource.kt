package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.Train
import com.ivzb.irish_rail.util.NetworkUtils
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Downloads and parses trains data.
 */
class RemoteTrainsDataSource @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val retrofit: Retrofit
) : TrainsDataSource {

    override fun fetchTrains(): List<Train>? {
        if (!networkUtils.hasNetworkConnection()) {
            return null
        }

        val response = retrofit.create<TrainsAPI>(TrainsAPI::class.java).fetchTrains().execute()

        return response.body()?.trains
    }
}
