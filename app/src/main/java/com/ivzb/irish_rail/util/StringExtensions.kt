package com.ivzb.irish_rail.util

fun String.capitalizeWords(): String =
    this
        .toLowerCase()
        .split(" ")
        .joinToString(" ") { it.capitalize() }