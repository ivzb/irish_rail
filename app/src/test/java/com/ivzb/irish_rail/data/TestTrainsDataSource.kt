package com.ivzb.irish_rail.data

import com.ivzb.irish_rail.data.trains.TrainsDataSource

class TestTrainsDataSource : TrainsDataSource {

    override fun fetchTrainPositions() = TestData.trainPositions

    override fun fetchTrainMovements(trainCode: String) = TestData.trainMovements[trainCode]
}
