package com.ivzb.irish_rail.data.stations

import com.ivzb.irish_rail.model.network.station.StationsDetailsResponse
import com.ivzb.irish_rail.model.network.station.StationsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StationsAPI {

    @GET("/realtime/realtime.asmx/getAllStationsXML")
    fun fetchStations(): Call<StationsResponse>

    @GET("/realtime/realtime.asmx/getStationDataByCodeXML_WithNumMins")
    fun fetchStationDetails(
        @Query("StationCode") stationCode: String,
        @Query("NumMins") numMins: Int = 90
    ): Call<StationsDetailsResponse>
}
