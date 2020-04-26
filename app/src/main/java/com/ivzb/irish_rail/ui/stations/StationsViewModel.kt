package com.ivzb.irish_rail.ui.stations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.domain.Event
import com.ivzb.irish_rail.domain.Result
import com.ivzb.irish_rail.domain.stations.FetchStationsUseCase
import com.ivzb.irish_rail.domain.successOr
import com.ivzb.irish_rail.model.ui.station.Station
import com.ivzb.irish_rail.ui.Empty
import com.ivzb.irish_rail.util.map
import javax.inject.Inject

class StationsViewModel @Inject constructor(
    private val fetchStationsUseCase: FetchStationsUseCase
): ViewModel() {

    val stations: LiveData<List<Any>>
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val stationClick: MutableLiveData<Event<Station>> = MutableLiveData()
    val searchQuery = MutableLiveData<String>()

    private val fetchStationsResult = MutableLiveData<Result<List<Station>?>>()

    init {
        stations = fetchStationsResult.map {
            // stop the loading indicator, whatever the result is
            loading.postValue(false)
            it.successOr(listOf(Empty)) ?: listOf(Empty)
        }

        fetchStations()
    }

    fun click(station: Station) {
        stationClick.postValue(Event(station))
    }

    fun fetchStations() {
        loading.postValue(true)
        fetchStationsUseCase(Unit, fetchStationsResult)
    }

    fun search(query: String?) {
        searchQuery.postValue(query ?: "")
    }
}
