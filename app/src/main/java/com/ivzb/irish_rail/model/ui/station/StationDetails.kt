package com.ivzb.irish_rail.model.ui.station

data class StationDetails(
    val trainCode: String,
    val stationName: String,
    val stationCode: String,
    val date: String,
    val time: String,
    val expectedArrival: String,
    val expectedDeparture: String,
    val scheduledArrival: String,
    val scheduledDeparture: String,
    val direction: String,
    val originName: String,
    val originTime: String,
    val destinationName: String,
    val destinationTime: String
)