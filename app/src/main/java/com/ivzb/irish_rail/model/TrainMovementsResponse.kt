package com.ivzb.irish_rail.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjTrainMovements", strict = false)
data class TrainMovementsResponse internal constructor(
    @field:ElementList(inline = true) var trainsMovements: List<TrainMovementResponse>? = null
)
