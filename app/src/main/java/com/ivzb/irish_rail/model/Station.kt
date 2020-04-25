package com.ivzb.irish_rail.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objStation")
data class Station(
    @field:Element(name = "StationId") var id: Long? = null,
    @field:Element(name = "StationCode") var code: String? = null,
    @field:Element(name = "StationAlias", required = false) var alias : String? = null,
    @field:Element(name = "StationDesc") var description: String? = null,
    @field:Element(name = "StationLatitude") var latitude: Double? = null,
    @field:Element(name = "StationLongitude") var longitude: Double? = null
)
