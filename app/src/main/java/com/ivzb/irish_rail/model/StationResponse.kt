package com.ivzb.irish_rail.model

import com.ivzb.irish_rail.util.capitalizeWords
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.util.*

@Root(name = "objStation", strict = false)
data class StationResponse(
    @field:Element(name = "StationId", required = false) var id: Long = 0,
    @field:Element(name = "StationCode", required = false) var code: String = "",
    @field:Element(name = "StationAlias", required = false) var alias : String? = null,
    @field:Element(name = "StationDesc", required = false) var description: String = "",
    @field:Element(name = "StationLatitude", required = false) var latitude: Double? = null,
    @field:Element(name = "StationLongitude", required = false) var longitude: Double? = null
) {

    fun asStation() = Station(
        id = this.id,
        code = this.code,
        name = this.description.capitalizeWords()
    )
}
