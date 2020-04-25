package com.ivzb.irish_rail.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainPositions", strict = false)
data class TrainPosition(
    @field:Element(name = "TrainCode", required = false) var code: String? = null,
    @field:Element(name = "TrainDate", required = false) var date: String? = null,
    @field:Element(name = "TrainStatus", required = false) var status: String? = null,
    @field:Element(name = "TrainLatitude", required = false) var latitude: Double? = null,
    @field:Element(name = "TrainLongitude", required = false) var longitude: Double? = null,
    @field:Element(name = "PublicMessage", required = false) var publicMessage: String? = null,
    @field:Element(name = "Direction", required = false) var direction: String? = null
)
