package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.ui.train.TrainMovement
import com.ivzb.irish_rail.model.ui.train.TrainPosition

interface TrainsDataSource {

    fun fetchTrainPositions(): List<TrainPosition>?

    fun fetchTrainMovements(trainCode: String): List<TrainMovement>?
}
