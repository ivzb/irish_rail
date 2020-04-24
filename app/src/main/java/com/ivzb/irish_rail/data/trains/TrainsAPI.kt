package com.ivzb.irish_rail.data.trains

import retrofit2.Call
import retrofit2.http.GET

interface TrainsAPI {

    @GET("/realtime/realtime.asmx/getCurrentTrainsXML")
    fun fetchTrains(): Call<TrainsResponse>
}