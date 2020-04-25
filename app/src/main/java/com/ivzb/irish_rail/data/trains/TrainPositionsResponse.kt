package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.TrainPosition
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjTrainPositions", strict = false)
data class TrainPositionsResponse internal constructor(
    @field:ElementList(inline = true) var trainPositions: List<TrainPosition>? = null
)
