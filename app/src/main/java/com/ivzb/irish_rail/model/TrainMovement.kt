package com.ivzb.irish_rail.model

data class TrainMovement(
    val code: String,
    val date: String,
    val origin: String,
    val destination: String,
    val locationCode: String,
    val locationName: String,
    val locationOrder: Int,
    val scheduledArrival: String,
    val scheduledDeparture: String,
    val expectedArrival: String,
    val expectedDeparture: String
)
