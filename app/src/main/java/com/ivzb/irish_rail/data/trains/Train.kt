package com.ivzb.irish_rail.data.trains

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainPositions")
data class Train(
    @field:Element(name = "TrainCode") var id: String? = null,
    @field:Element(name = "TrainDate") var date: String? = null,
    @field:Element(name = "TrainStatus") var status: String? = null,
    @field:Element(name = "TrainLatitude") var lat: Double? = null,
    @field:Element(name = "TrainLongitude") var lng: Double? = null,
    @field:Element(name = "PublicMessage") var code: String? = null,
    @field:Element(name = "Direction") var direction: String? = null
)
