package com.ivzb.irish_rail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ivzb.irish_rail.data.TestData
import com.ivzb.irish_rail.data.TestTrainsDataSource
import com.ivzb.irish_rail.data.trains.TrainsRepository
import com.ivzb.irish_rail.domain.trains.FetchTrainMovementsUseCase
import com.ivzb.irish_rail.model.ui.train.TrainMovement
import com.ivzb.irish_rail.ui.train_movements.TrainMovementsViewModel
import com.ivzb.irish_rail.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class TrainMovementsViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val trainsDataSource = TestTrainsDataSource()
    private val trainsRepository = TrainsRepository(trainsDataSource)
    private val fetchTrainMovementsUseCase = FetchTrainMovementsUseCase(trainsRepository)

    @Test
    fun testFetchTrainMovements() {
        // arrange
        val viewModel = TrainMovementsViewModel(fetchTrainMovementsUseCase)

        // act
        viewModel.fetchTrains(TestData.trainMovement.code)

        // assert
        val trainMovementsObservable = LiveDataTestUtil.getValue(viewModel.trains)
        Assert.assertThat(trainMovementsObservable as? List<TrainMovement>, `is`(equalTo(TestData.trainMovements[TestData.trainMovement.code])))

        val loadingObservable = LiveDataTestUtil.getValue(viewModel.loading)
        Assert.assertThat(loadingObservable, `is`(equalTo(false)))
    }

    @Test
    fun testClick() {
        // arrange
        val viewModel = TrainMovementsViewModel(fetchTrainMovementsUseCase)

        // act
        viewModel.click(TestData.trainMovement)

        // assert
        val trainClickObservable = LiveDataTestUtil.getValue(viewModel.trainClick)
        Assert.assertThat(trainClickObservable?.peekContent(), `is`(equalTo(TestData.trainMovement)))
    }

    @Test
    fun searchQuery() {
        // arrange
        val viewModel = TrainMovementsViewModel(fetchTrainMovementsUseCase)

        // act
        viewModel.search(TestData.searchQuery)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo(TestData.searchQuery)))
    }

    @Test
    fun searchQueryWithEmptyString() {
        // arrange
        val viewModel = TrainMovementsViewModel(fetchTrainMovementsUseCase)

        // act
        viewModel.search("")

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }

    @Test
    fun searchQueryWithNull() {
        // arrange
        val viewModel = TrainMovementsViewModel(fetchTrainMovementsUseCase)

        // act
        viewModel.search(null)

        // assert
        val searchQueryObservable = LiveDataTestUtil.getValue(viewModel.searchQuery)
        Assert.assertThat(searchQueryObservable, `is`(equalTo("")))
    }
}
