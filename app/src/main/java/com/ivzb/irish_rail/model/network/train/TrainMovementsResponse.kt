package com.ivzb.irish_rail.model.network.train

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjTrainMovements", strict = false)
data class TrainMovementsResponse internal constructor(
    @field:ElementList(inline = true, required = false) var trainsMovements: List<TrainMovementResponse>? = null
)
