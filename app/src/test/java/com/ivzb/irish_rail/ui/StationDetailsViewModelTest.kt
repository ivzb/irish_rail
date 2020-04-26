package com.ivzb.irish_rail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ivzb.irish_rail.data.TestData
import com.ivzb.irish_rail.data.TestStationsDataSource
import com.ivzb.irish_rail.data.stations.StationsRepository
import com.ivzb.irish_rail.domain.stations.FetchStationDetailsUseCase
import com.ivzb.irish_rail.model.ui.station.StationDetails
import com.ivzb.irish_rail.ui.station_details.StationDetailsViewModel
import com.ivzb.irish_rail.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class StationDetailsViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val stationDetailsDataSource = TestStationsDataSource()
    private val stationDetailsRepository = StationsRepository(stationDetailsDataSource)
    private val fetchStationDetailsUseCase = FetchStationDetailsUseCase(stationDetailsRepository)

    @Test
    fun testFetchStationDetails() {
        // arrange
        val viewModel = StationDetailsViewModel(fetchStationDetailsUseCase)

        // act
        viewModel.fetchStationDetails(TestData.stationDetail.stationCode)

        // assert
        val stationDetailsObservable = LiveDataTestUtil.getValue(viewModel.stationDetails)
        Assert.assertThat(stationDetailsObservable as? List<StationDetails>, `is`(equalTo(TestData.stationDetails[TestData.stationDetail.stationCode])))

        val loadingObservable = LiveDataTestUtil.getValue(viewModel.loading)
        Assert.assertThat(loadingObservable, `is`(equalTo(false)))
    }

    @Test
    fun testClick() {
        // arrange
        val viewModel = StationDetailsViewModel(fetchStationDetailsUseCase)

        // act
        viewModel.click(TestData.stationDetail)

        // assert
        val stationDetailsClickObservable = LiveDataTestUtil.getValue(viewModel.stationDetailsClick)
        Assert.assertThat(stationDetailsClickObservable?.peekContent(), `is`(equalTo(TestData.stationDetail)))
    }

    @Test
    fun searchQuery() {
        // arrange
        val viewModel = StationDetailsViewModel(fetchStationDetailsUseCase)

        // act
        viewModel.search(TestData.searchQuery)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo(TestData.searchQuery)))
    }

    @Test
    fun searchQueryWithEmptyString() {
        // arrange
        val viewModel = StationDetailsViewModel(fetchStationDetailsUseCase)

        // act
        viewModel.search("")

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }

    @Test
    fun searchQueryWithNull() {
        // arrange
        val viewModel = StationDetailsViewModel(fetchStationDetailsUseCase)

        // act
        viewModel.search(null)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }
}
