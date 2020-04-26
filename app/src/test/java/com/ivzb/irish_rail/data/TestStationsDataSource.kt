package com.ivzb.irish_rail.data

import com.ivzb.irish_rail.data.stations.StationsDataSource

class TestStationsDataSource : StationsDataSource {

    override fun fetchStations() = TestData.stations

    override fun fetchStationDetails(stationCode: String) = TestData.stationDetails[stationCode]
}
