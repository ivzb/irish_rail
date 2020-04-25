package com.ivzb.irish_rail.data.stations

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single point of access to stations data for the presentation layer.
 */
@Singleton
open class StationsRepository @Inject constructor(
    private val dataSource: StationsDataSource
) {

    fun fetchStations() = dataSource.fetchStations()
}
