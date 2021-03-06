package com.ivzb.irish_rail.ui.train_movements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.domain.Event
import com.ivzb.irish_rail.domain.Result
import com.ivzb.irish_rail.domain.trains.FetchTrainMovementsUseCase
import com.ivzb.irish_rail.model.ui.train.TrainMovement
import com.ivzb.irish_rail.util.map
import com.ivzb.irish_rail.util.mapFetchResult
import javax.inject.Inject

class TrainMovementsViewModel @Inject constructor(
    private val fetchTrainMovementsUseCase: FetchTrainMovementsUseCase
) : ViewModel() {

    val trains: LiveData<List<Any>>
    val loading = MutableLiveData<Boolean>()
    val trainClick = MutableLiveData<Event<TrainMovement>>()
    val searchQuery = MutableLiveData<String>()

    private val fetchTrainsResult = MutableLiveData<Result<List<TrainMovement>?>>()

    init {
        trains = fetchTrainsResult.map {
            // stop the loading indicator, whatever the result is
            loading.postValue(false)

            mapFetchResult(it)
        }
    }

    fun click(trainMovement: TrainMovement) {
        trainClick.postValue(Event(trainMovement))
    }

    fun fetchTrains(trainId: String) {
        loading.postValue(true)
        fetchTrainMovementsUseCase(trainId, fetchTrainsResult)
    }

    fun search(query: String?) {
        searchQuery.postValue(query ?: "")
    }
}
