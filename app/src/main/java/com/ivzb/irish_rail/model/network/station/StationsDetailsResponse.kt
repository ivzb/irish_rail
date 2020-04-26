package com.ivzb.irish_rail.model.network.station

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfObjStationData", strict = false)
data class StationsDetailsResponse internal constructor(
    @field:ElementList(inline = true, required = false) var stationDetails: List<StationDetailsResponse>? = null
)
