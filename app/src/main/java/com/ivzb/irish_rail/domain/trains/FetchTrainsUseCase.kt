package com.ivzb.irish_rail.domain.trains

import com.ivzb.irish_rail.data.trains.Train
import com.ivzb.irish_rail.data.trains.TrainsRepository
import com.ivzb.irish_rail.domain.UseCase
import javax.inject.Inject

open class FetchTrainsUseCase @Inject constructor(
    private val repository: TrainsRepository
) : UseCase<Unit, List<Train>>() {

    override fun execute(parameters: Unit): List<Train>
            = repository.fetchTrains()
}
