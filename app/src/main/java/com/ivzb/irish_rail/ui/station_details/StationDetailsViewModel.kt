package com.ivzb.irish_rail.ui.station_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivzb.irish_rail.domain.Event
import com.ivzb.irish_rail.domain.Result
import com.ivzb.irish_rail.domain.stations.FetchStationDetailsUseCase
import com.ivzb.irish_rail.domain.successOr
import com.ivzb.irish_rail.model.ui.station.StationDetails
import com.ivzb.irish_rail.ui.Empty
import com.ivzb.irish_rail.ui.NoConnection
import com.ivzb.irish_rail.util.map
import javax.inject.Inject

class StationDetailsViewModel @Inject constructor(
    private val fetchStationDetailsUseCase: FetchStationDetailsUseCase
) : ViewModel() {

    val stationDetails: LiveData<List<Any>>
    val loading= MutableLiveData<Boolean>()
    val stationDetailsClick= MutableLiveData<Event<StationDetails>>()
    val searchQuery = MutableLiveData<String>()

    private val fetchStationDetailsResult = MutableLiveData<Result<List<StationDetails>?>>()

    init {
        stationDetails = fetchStationDetailsResult.map {
            // stop the loading indicator, whatever the result is
            loading.postValue(false)
            it?.successOr(listOf(NoConnection)) ?: listOf(Empty)
        }
    }

    fun click(stationDetails: StationDetails) {
        stationDetailsClick.postValue(Event(stationDetails))
    }

    fun fetchStationDetails(stationCode: String) {
        loading.postValue(true)
        fetchStationDetailsUseCase(stationCode, fetchStationDetailsResult)
    }

    fun search(query: String?) {
        searchQuery.postValue(query ?: "")
    }
}
