package com.ivzb.irish_rail.data.stations

import com.ivzb.irish_rail.model.Station
import com.ivzb.irish_rail.model.StationDetails

interface StationsDataSource {

    fun fetchStations(): List<Station>?

    fun fetchStationDetails(stationCode: String): List<StationDetails>?
}
