package com.ivzb.irish_rail.data.trains

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single point of access to trains data for the presentation layer.
 */
@Singleton
open class TrainsRepository @Inject constructor(
    private val dataSource: TrainsDataSource
) {

    fun fetchTrains() = dataSource.fetchTrains()
}
