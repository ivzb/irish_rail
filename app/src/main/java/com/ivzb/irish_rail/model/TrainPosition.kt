package com.ivzb.irish_rail.model

data class TrainPosition(
    val code: String,
    val date: String,
    val status: TrainStatus,
    val message: String,
    val direction: String
)