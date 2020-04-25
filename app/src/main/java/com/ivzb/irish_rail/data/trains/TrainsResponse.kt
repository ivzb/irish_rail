package com.ivzb.irish_rail.data.trains

import com.ivzb.irish_rail.model.Train
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjTrainPositions", strict = false)
data class TrainsResponse internal constructor(
    @field:ElementList(inline = true) var trains: List<Train>? = null
)
