package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.Train

interface TrainsDataSource {

    fun fetchTrains(): List<Train>
}
