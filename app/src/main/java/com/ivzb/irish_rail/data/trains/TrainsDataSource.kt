package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.TrainMovement
import com.ivzb.irish_rail.model.TrainPosition

interface TrainsDataSource {

    fun fetchTrainPositions(): List<TrainPosition>?

    fun fetchTrainMovements(trainId: String): List<TrainMovement>?
}
