package com.ivzb.irish_rail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ivzb.irish_rail.data.TestData
import com.ivzb.irish_rail.data.TestStationsDataSource
import com.ivzb.irish_rail.data.stations.StationsRepository
import com.ivzb.irish_rail.domain.stations.FetchStationsUseCase
import com.ivzb.irish_rail.model.ui.station.Station
import com.ivzb.irish_rail.ui.stations.StationsViewModel
import com.ivzb.irish_rail.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class StationsViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val stationsDataSource = TestStationsDataSource()
    private val stationsRepository = StationsRepository(stationsDataSource)
    private val fetchStationsUseCase = FetchStationsUseCase(stationsRepository)

    @Test
    fun testFetchStations() {
        // arrange
        val viewModel = StationsViewModel(fetchStationsUseCase)

        // act
        viewModel.fetchStations()

        // assert
        val stationsObservable = LiveDataTestUtil.getValue(viewModel.stations)
        Assert.assertThat(stationsObservable as? List<Station>, `is`(equalTo(TestData.stations)))

        val loadingObservable = LiveDataTestUtil.getValue(viewModel.loading)
        Assert.assertThat(loadingObservable, `is`(equalTo(false)))
    }

    @Test
    fun testClick() {
        // arrange
        val viewModel = StationsViewModel(fetchStationsUseCase)

        // act
        viewModel.click(TestData.station)

        // assert
        val stationClickObservable = LiveDataTestUtil.getValue(viewModel.stationClick)
        Assert.assertThat(stationClickObservable?.peekContent(), `is`(equalTo(TestData.station)))
    }

    @Test
    fun searchQuery() {
        // arrange
        val viewModel = StationsViewModel(fetchStationsUseCase)

        // act
        viewModel.search(TestData.searchQuery)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo(TestData.searchQuery)))
    }

    @Test
    fun searchQueryWithEmptyString() {
        // arrange
        val viewModel = StationsViewModel(fetchStationsUseCase)

        // act
        viewModel.search("")

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }

    @Test
    fun searchQueryWithNull() {
        // arrange
        val viewModel = StationsViewModel(fetchStationsUseCase)

        // act
        viewModel.search(null)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }
}