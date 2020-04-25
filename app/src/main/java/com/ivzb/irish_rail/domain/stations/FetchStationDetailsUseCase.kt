package com.ivzb.irish_rail.domain.stations

import com.ivzb.irish_rail.data.stations.StationsRepository
import com.ivzb.irish_rail.domain.UseCase
import com.ivzb.irish_rail.model.StationDetails
import javax.inject.Inject

open class FetchStationDetailsUseCase @Inject constructor(
    private val repository: StationsRepository
) : UseCase<String, List<StationDetails>?>() {

    override fun execute(parameters: String): List<StationDetails>?
            = repository.fetchStationDetails(parameters)
}
