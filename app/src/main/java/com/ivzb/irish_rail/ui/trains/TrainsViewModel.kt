package com.ivzb.irish_rail.ui.trains

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.data.trains.Train
import com.ivzb.irish_rail.domain.Result
import com.ivzb.irish_rail.domain.successOr
import com.ivzb.irish_rail.domain.trains.FetchTrainsUseCase
import com.ivzb.irish_rail.util.map
import javax.inject.Inject

class TrainsViewModel @Inject constructor(
    fetchTrainsUseCase: FetchTrainsUseCase
) : ViewModel() {

    val trains: LiveData<List<Any>>

    private val fetchTrainsResult = MutableLiveData<Result<List<Train>>>()

    init {
        fetchTrainsUseCase(Unit, fetchTrainsResult)

        trains = fetchTrainsResult.map {
            if (it is Result.Loading) {
                // TODO: Return actual loading indicator
//                listOf(LoadingIndicator)
                listOf()
            } else {
                it.successOr(emptyList())
            }
        }
    }
}
