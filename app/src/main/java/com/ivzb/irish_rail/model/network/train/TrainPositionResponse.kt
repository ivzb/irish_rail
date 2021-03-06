package com.ivzb.irish_rail.model.network.train

import com.ivzb.irish_rail.model.ui.train.TrainPosition
import com.ivzb.irish_rail.model.ui.train.TrainStatus
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainPositions", strict = false)
data class TrainPositionResponse(
    @field:Element(name = "TrainCode", required = false) var code: String = "",
    @field:Element(name = "TrainDate", required = false) var date: String = "",
    @field:Element(name = "TrainStatus", required = false) var status: String = "",
    @field:Element(name = "TrainLatitude", required = false) var latitude: Double? = null,
    @field:Element(name = "TrainLongitude", required = false) var longitude: Double? = null,
    @field:Element(name = "PublicMessage", required = false) var publicMessage: String = "",
    @field:Element(name = "Direction", required = false) var direction: String = ""
) {

    fun asTrainPosition() = TrainPosition(
        code = this.code,
        date = this.date,
        status = when (this.status) {
            "N" -> TrainStatus.NotRunningYet
            "R" -> TrainStatus.Running
            else -> TrainStatus.Unknown
        },
        message = this.publicMessage.replace("\\n", "\n"),
        direction = this.direction
    )
}
