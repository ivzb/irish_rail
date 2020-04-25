package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.TrainMovement
import com.ivzb.irish_rail.model.TrainPosition
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

    override fun fetchTrainPositions(): List<TrainPosition>? {
        if (!networkUtils.hasNetworkConnection()) {
            return null
        }

        val response = retrofit.create<TrainsAPI>(TrainsAPI::class.java).fetchTrainPositions().execute()

        return response.body()?.trainPositions
    }

    override fun fetchTrainMovements(traindId: String): List<TrainMovement>? {
        if (!networkUtils.hasNetworkConnection()) {
            return null
        }

        val response = retrofit.create<TrainsAPI>(TrainsAPI::class.java).fetchTrainMovements(traindId).execute()

        return response.body()?.trainsMovements
    }
}
