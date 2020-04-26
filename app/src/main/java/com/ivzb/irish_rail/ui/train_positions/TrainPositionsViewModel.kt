package com.ivzb.irish_rail.ui.train_positions

import androidx.lifecycle.*
import com.ivzb.irish_rail.domain.Event
import com.ivzb.irish_rail.domain.Result
import com.ivzb.irish_rail.domain.successOr
import com.ivzb.irish_rail.domain.trains.FetchTrainPositionsUseCase
import com.ivzb.irish_rail.model.ui.train.TrainPosition
import com.ivzb.irish_rail.ui.Empty
import com.ivzb.irish_rail.util.map
import com.ivzb.irish_rail.util.switchMap
import javax.inject.Inject

class TrainPositionsViewModel @Inject constructor(
    private val fetchTrainPositionsUseCase: FetchTrainPositionsUseCase
) : ViewModel() {

    val trains: LiveData<List<Any>>

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    val trainClick: MutableLiveData<Event<TrainPosition>> = MutableLiveData()

    val searchQuery = MutableLiveData<String>()

    private val fetchTrainsResult = MutableLiveData<Result<List<TrainPosition>?>>()

    init {
        trains = fetchTrainsResult
            .map {
                // stop the loading indicator, whatever the result is
                loading.postValue(false)
                it?.successOr(listOf(Empty)) ?: listOf(Empty)
            }

        fetchTrains()
    }

    fun click(trainPosition: TrainPosition) {
        trainClick.postValue(Event(trainPosition))
    }

    fun fetchTrains() {
        loading.postValue(true)
        fetchTrainPositionsUseCase(Unit, fetchTrainsResult)
    }

    fun search(query: String?) {
        searchQuery.postValue(query ?: "")
    }
}
