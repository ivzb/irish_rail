package com.ivzb.irish_rail.util

import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.domain.Result
import com.ivzb.irish_rail.domain.successOr
import com.ivzb.irish_rail.ui.Empty
import com.ivzb.irish_rail.ui.NoConnection

fun <T : Any> ViewModel.mapFetchResult(it: Result<List<T>?>): List<Any> {
    val result = it.successOr(listOf(NoConnection)) ?: listOf(Empty)

    return when {
        result.isEmpty() -> listOf(Empty)
        else -> result
    }
}
