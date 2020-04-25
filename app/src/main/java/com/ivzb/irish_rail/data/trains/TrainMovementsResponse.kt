package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.TrainMovement
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjTrainMovements", strict = false)
data class TrainMovementsResponse internal constructor(
    @field:ElementList(inline = true) var trainsMovements: List<TrainMovement>? = null
)
