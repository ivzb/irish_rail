package com.ivzb.irish_rail.data.trains

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrainsAPI {

    @GET("/realtime/realtime.asmx/getCurrentTrainsXML")
    fun fetchTrainPositions(): Call<TrainPositionsResponse>

    @GET("/realtime/realtime.asmx/getTrainMovementsXML")
    fun fetchTrainMovements(@Query("TrainId") trainId: String): Call<TrainMovementsResponse>
}