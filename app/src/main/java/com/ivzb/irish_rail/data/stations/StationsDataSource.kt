package com.ivzb.irish_rail.data.stations

import com.ivzb.irish_rail.model.ui.station.Station
import com.ivzb.irish_rail.model.ui.station.StationDetails

interface StationsDataSource {

    fun fetchStations(): List<Station>?

    fun fetchStationDetails(stationCode: String): List<StationDetails>?
}
