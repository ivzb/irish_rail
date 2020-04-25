package com.ivzb.irish_rail.model.network.station

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjStation", strict = false)
data class StationsResponse internal constructor(
    @field:ElementList(inline = true) var stations: List<StationResponse>? = null
)
