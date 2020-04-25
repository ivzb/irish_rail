package com.ivzb.irish_rail.model.ui.train

data class TrainPosition(
    val code: String,
    val date: String,
    val status: TrainStatus,
    val message: String,
    val direction: String
)

enum class TrainStatus(val value: String?) {
    NotRunningYet("Not running yet"),
    Running("Running"),
    Unknown("Unknown")
}
