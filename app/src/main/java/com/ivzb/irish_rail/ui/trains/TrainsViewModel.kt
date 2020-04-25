package com.ivzb.irish_rail.ui.trains

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.model.Train
import com.ivzb.irish_rail.domain.Result
import com.ivzb.irish_rail.domain.successOr
import com.ivzb.irish_rail.domain.trains.FetchTrainsUseCase
import com.ivzb.irish_rail.util.map
import javax.inject.Inject

class TrainsViewModel @Inject constructor(
    private val fetchTrainsUseCase: FetchTrainsUseCase
) : ViewModel() {

    val trains: LiveData<List<Any>>

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    private val fetchTrainsResult = MutableLiveData<Result<List<Train>>>()

    init {
        trains = fetchTrainsResult.map {
            // stop the loading indicator, whatever the result is
            loading.postValue(false)
            it.successOr(emptyList())
        }

        fetchTrains()
    }

    fun click(train: Train) {

    }

    fun fetchTrains() {
        loading.postValue(true)
        fetchTrainsUseCase(Unit, fetchTrainsResult)
    }
}
