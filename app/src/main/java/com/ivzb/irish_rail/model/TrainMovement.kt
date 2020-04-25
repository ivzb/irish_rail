package com.ivzb.irish_rail.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainMovements")
data class TrainMovement(
    @field:Element(name = "TrainCode") var code: String? = null,
    @field:Element(name = "TrainDate") var date: String? = null,
    @field:Element(name = "TrainOrigin") var origin: String? = null,
    @field:Element(name = "TrainDestination") var destination: String? = null,
    @field:Element(name = "LocationCode") var locationCode: String? = null,
    @field:Element(name = "LocationFullName") var locationFullName: String? = null,
    @field:Element(name = "LocationOrder") var locationOrder: Int? = 0,
    @field:Element(name = "LocationType") var locationType: String? = null,
    @field:Element(name = "ScheduledArrival") var scheduledArrival: String? = null,
    @field:Element(name = "ScheduledDeparture") var scheduledDeparture: String? = null,
    @field:Element(name = "ExpectedArrival") var expectedArrival: String? = null,
    @field:Element(name = "ExpectedDeparture") var expectedDeparture: String? = null,
    @field:Element(name = "Arrival") var arrival: String? = null,
    @field:Element(name = "Departure") var departure: String? = null,
    @field:Element(name = "AutoArrival") var autoArrival: Int? = null,
    @field:Element(name = "AutoDepart") var autoDepart: Int? = null,
    @field:Element(name = "StopType") var stopType: String? = null
)
