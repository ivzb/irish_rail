package com.ivzb.irish_rail.data.stations

import com.ivzb.irish_rail.model.StationsDetailsResponse
import com.ivzb.irish_rail.model.StationsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StationsAPI {

    @GET("/realtime/realtime.asmx/getAllStationsXML")
    fun fetchStations(): Call<StationsResponse>

    @GET("/realtime/realtime.asmx/getStationDataByCodeXML")
    fun fetchStationDetails(
        @Query("StationCode") stationCode: String
    ): Call<StationsDetailsResponse>
}
