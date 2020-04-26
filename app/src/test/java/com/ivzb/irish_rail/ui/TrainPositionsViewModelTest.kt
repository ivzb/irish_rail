package com.ivzb.irish_rail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ivzb.irish_rail.data.TestData
import com.ivzb.irish_rail.data.TestTrainsDataSource
import com.ivzb.irish_rail.data.trains.TrainsRepository
import com.ivzb.irish_rail.domain.trains.FetchTrainPositionsUseCase
import com.ivzb.irish_rail.model.ui.train.TrainPosition
import com.ivzb.irish_rail.ui.train_positions.TrainPositionsViewModel
import com.ivzb.irish_rail.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class TrainPositionsViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val trainsDataSource = TestTrainsDataSource()
    private val trainsRepository = TrainsRepository(trainsDataSource)
    private val fetchTrainPositionsUseCase = FetchTrainPositionsUseCase(trainsRepository)

    @Test
    fun testFetchTrainPositions() {
        // arrange
        val viewModel = TrainPositionsViewModel(fetchTrainPositionsUseCase)

        // act
        viewModel.fetchTrains()

        // assert
        val trainPositionsObservable = LiveDataTestUtil.getValue(viewModel.trains)
        Assert.assertThat(trainPositionsObservable as? List<TrainPosition>, `is`(equalTo(TestData.trainPositions)))

        val loadingObservable = LiveDataTestUtil.getValue(viewModel.loading)
        Assert.assertThat(loadingObservable, `is`(equalTo(false)))
    }

    @Test
    fun testClick() {
        // arrange
        val viewModel = TrainPositionsViewModel(fetchTrainPositionsUseCase)

        // act
        viewModel.click(TestData.trainPosition)

        // assert
        val trainClickObservable = LiveDataTestUtil.getValue(viewModel.trainClick)
        Assert.assertThat(trainClickObservable?.peekContent(), `is`(equalTo(TestData.trainPosition)))
    }

    @Test
    fun searchQuery() {
        // arrange
        val viewModel = TrainPositionsViewModel(fetchTrainPositionsUseCase)

        // act
        viewModel.search(TestData.searchQuery)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo(TestData.searchQuery)))
    }

    @Test
    fun searchQueryWithEmptyString() {
        // arrange
        val viewModel = TrainPositionsViewModel(fetchTrainPositionsUseCase)

        // act
        viewModel.search("")

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }

    @Test
    fun searchQueryWithNull() {
        // arrange
        val viewModel = TrainPositionsViewModel(fetchTrainPositionsUseCase)

        // act
        viewModel.search(null)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }
}
