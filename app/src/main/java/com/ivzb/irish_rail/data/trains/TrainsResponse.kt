package com.ivzb.irish_rail.data.trains

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjTrainPositions", strict = false)
data class TrainsResponse internal constructor(
    @field:ElementList(inline = true) var trains: List<Train>? = null
)
