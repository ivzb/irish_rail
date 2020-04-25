package com.ivzb.irish_rail.domain.trains

import com.ivzb.irish_rail.data.trains.TrainsRepository
import com.ivzb.irish_rail.domain.UseCase
import com.ivzb.irish_rail.model.ui.train.TrainPosition
import javax.inject.Inject

open class FetchTrainPositionsUseCase @Inject constructor(
    private val repository: TrainsRepository
) : UseCase<Unit, List<TrainPosition>?>() {

    override fun execute(parameters: Unit): List<TrainPosition>?
            = repository.fetchTrainPositions()
}
