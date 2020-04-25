package com.ivzb.irish_rail.model.network.train

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjTrainPositions", strict = false)
data class TrainPositionsResponse internal constructor(
    @field:ElementList(inline = true) var trainPositions: List<TrainPositionResponse>? = null
)
