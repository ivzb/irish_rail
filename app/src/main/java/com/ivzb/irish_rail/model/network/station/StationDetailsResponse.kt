package com.ivzb.irish_rail.model.network.station

import com.ivzb.irish_rail.model.ui.station.StationDetails
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objStationData", strict = false)
data class StationDetailsResponse(
    @field:Element(name = "Servertime", required = false) var servertime: String = "",
    @field:Element(name = "Traincode", required = false) var trainCode: String = "",
    @field:Element(name = "Stationfullname", required = false) var stationName: String = "",
    @field:Element(name = "Stationcode", required = false) var stationCode: String = "",
    @field:Element(name = "Querytime", required = false) var time: String = "",
    @field:Element(name = "Traindate", required = false) var date: String = "",
    @field:Element(name = "Origin", required = false) var originName: String = "",
    @field:Element(name = "Origintime", required = false) var originTime: String = "",
    @field:Element(name = "Destination", required = false) var destinationName: String = "",
    @field:Element(name = "Destinationtime", required = false) var destinationTime: String = "",
    @field:Element(name = "Status", required = false) var status: String = "",
    @field:Element(name = "Lastlocation", required = false) var lastLocation: String? = null,
    @field:Element(name = "Duein", required = false) var dueIn: Int = 0,
    @field:Element(name = "Late", required = false) var late: Int = 0,
    @field:Element(name = "Exparrival", required = false) var expectedArrival: String = "",
    @field:Element(name = "Expdepart", required = false) var expectedDeparture: String = "",
    @field:Element(name = "Scharrival", required = false) var scheduledArrival: String = "",
    @field:Element(name = "Schdepart", required = false) var scheduledDeparture: String = "",
    @field:Element(name = "Direction", required = false) var direction: String = "",
    @field:Element(name = "Traintype", required = false) var trainType: String = "",
    @field:Element(name = "Locationtype", required = false) var locationType: String = ""
) {

    fun asStationDetails() = StationDetails(
        trainCode = this.trainCode,
        stationName = this.stationName,
        stationCode = this.stationCode,
        date = this.date,
        time = this.time,
        expectedArrival = this.expectedArrival,
        expectedDeparture = this.expectedDeparture,
        scheduledArrival = this.scheduledArrival,
        scheduledDeparture = this.scheduledDeparture,
        direction = this.direction,
        originName = this.originName,
        originTime = this.originTime,
        destinationName = this.destinationName,
        destinationTime = this.destinationTime
    )
}
