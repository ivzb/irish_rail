package com.ivzb.irish_rail.domain.trains

import com.ivzb.irish_rail.data.trains.TrainsRepository
import com.ivzb.irish_rail.domain.UseCase
import com.ivzb.irish_rail.model.ui.train.TrainMovement
import javax.inject.Inject

open class FetchTrainMovementsUseCase @Inject constructor(
    private val repository: TrainsRepository
) : UseCase<String, List<TrainMovement>?>() {

    override fun execute(parameters: String): List<TrainMovement>?
            = repository.fetchTrainMovements(parameters)
}
