package com.ivzb.irish_rail.model.network.train

import com.ivzb.irish_rail.model.ui.train.TrainMovement
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainMovements", strict = false)
data class TrainMovementResponse(
    @field:Element(name = "TrainCode", required = false) var code: String = "",
    @field:Element(name = "TrainDate", required = false) var date: String = "",
    @field:Element(name = "TrainOrigin", required = false) var origin: String = "",
    @field:Element(name = "TrainDestination", required = false) var destination: String = "",
    @field:Element(name = "LocationCode", required = false) var locationCode: String = "",
    @field:Element(name = "LocationFullName", required = false) var locationFullName: String = "",
    @field:Element(name = "LocationOrder", required = false) var locationOrder: Int = 0,
    @field:Element(name = "LocationType", required = false) var locationType: String? = null,
    @field:Element(name = "ScheduledArrival", required = false) var scheduledArrival: String = "",
    @field:Element(name = "ScheduledDeparture", required = false) var scheduledDeparture: String = "",
    @field:Element(name = "ExpectedArrival", required = false) var expectedArrival: String = "",
    @field:Element(name = "ExpectedDeparture", required = false) var expectedDeparture: String = "",
    @field:Element(name = "Arrival", required = false) var arrival: String? = null,
    @field:Element(name = "Departure", required = false) var departure: String? = null,
    @field:Element(name = "AutoArrival", required = false) var autoArrival: Int? = null,
    @field:Element(name = "AutoDepart", required = false) var autoDepart: Int? = null,
    @field:Element(name = "StopType", required = false) var stopType: String? = null
) {

    fun asTrainMovement() = TrainMovement(
        code = this.code,
        date = this.date,
        origin = this.origin,
        destination = this.destination,
        locationCode = this.locationCode,
        locationName = this.locationFullName,
        locationOrder = this.locationOrder,
        scheduledArrival = this.scheduledArrival,
        scheduledDeparture = this.scheduledDeparture,
        expectedArrival = this.expectedArrival,
        expectedDeparture = this.expectedDeparture
    )
}
