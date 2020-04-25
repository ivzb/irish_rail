package com.ivzb.irish_rail.data.stations

import com.ivzb.irish_rail.model.StationsResponse
import retrofit2.Call
import retrofit2.http.GET

interface StationsAPI {

    @GET("/realtime/realtime.asmx/getAllStationsXML")
    fun fetchStations(): Call<StationsResponse>
}
