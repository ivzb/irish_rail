package com.ivzb.irish_rail.data.stations

import com.ivzb.irish_rail.model.Station

interface StationsDataSource {

    fun fetchStations(): List<Station>?
}
