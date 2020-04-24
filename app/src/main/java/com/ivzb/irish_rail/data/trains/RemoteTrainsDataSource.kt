package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.util.NetworkUtils
import javax.inject.Inject

/**
 * Downloads and parses trains data.
 */
class RemoteTrainsDataSource @Inject constructor(
    private val networkUtils: NetworkUtils
): TrainsDataSource {

    override fun fetchTrains(): List<Train> {
        if (!networkUtils.hasNetworkConnection()) {
            return listOf()
        }

        return listOf()
    }
}
