package com.ivzb.irish_rail.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainMovements", strict = false)
data class TrainMovement(
    @field:Element(name = "TrainCode", required = false) var code: String? = null,
    @field:Element(name = "TrainDate", required = false) var date: String? = null,
    @field:Element(name = "TrainOrigin", required = false) var origin: String? = null,
    @field:Element(name = "TrainDestination", required = false) var destination: String? = null,
    @field:Element(name = "LocationCode", required = false) var locationCode: String? = null,
    @field:Element(name = "LocationFullName", required = false) var locationFullName: String? = null,
    @field:Element(name = "LocationOrder", required = false) var locationOrder: Int? = 0,
    @field:Element(name = "LocationType", required = false) var locationType: String? = null,
    @field:Element(name = "ScheduledArrival", required = false) var scheduledArrival: String? = null,
    @field:Element(name = "ScheduledDeparture", required = false) var scheduledDeparture: String? = null,
    @field:Element(name = "ExpectedArrival", required = false) var expectedArrival: String? = null,
    @field:Element(name = "ExpectedDeparture", required = false) var expectedDeparture: String? = null,
    @field:Element(name = "Arrival", required = false) var arrival: String? = null,
    @field:Element(name = "Departure", required = false) var departure: String? = null,
    @field:Element(name = "AutoArrival", required = false) var autoArrival: Int? = null,
    @field:Element(name = "AutoDepart", required = false) var autoDepart: Int? = null,
    @field:Element(name = "StopType", required = false) var stopType: String? = null
)
