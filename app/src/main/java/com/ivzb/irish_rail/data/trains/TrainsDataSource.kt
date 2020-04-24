package com.ivzb.irish_rail.data.trains

interface TrainsDataSource {

    fun fetchTrains(): List<Train>
}
