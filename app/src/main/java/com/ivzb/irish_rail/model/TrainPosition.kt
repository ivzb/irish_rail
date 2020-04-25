package com.ivzb.irish_rail.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainPositions")
data class TrainPosition(
    @field:Element(name = "TrainCode") var code: String? = null,
    @field:Element(name = "TrainDate") var date: String? = null,
    @field:Element(name = "TrainStatus") var status: String? = null,
    @field:Element(name = "TrainLatitude") var latitude: Double? = null,
    @field:Element(name = "TrainLongitude") var longitude: Double? = null,
    @field:Element(name = "PublicMessage") var publicMessage: String? = null,
    @field:Element(name = "Direction") var direction: String? = null
)
