package com.ivzb.irish_rail.domain.stations

import com.ivzb.irish_rail.data.stations.StationsRepository
import com.ivzb.irish_rail.domain.UseCase
import com.ivzb.irish_rail.model.Station
import javax.inject.Inject

open class FetchStationsUseCase @Inject constructor(
    private val repository: StationsRepository
) : UseCase<Unit, List<Station>?>() {

    override fun execute(parameters: Unit): List<Station>? = repository.fetchStations()
}
