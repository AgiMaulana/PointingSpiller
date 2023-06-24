package presentation

import app.cash.turbine.test
import datafactories.newPointingPoker
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import presentation.PointingSpillerViewModel.State.Progress
import presentation.PointingSpillerViewModel.State.Initial
import presentation.PointingSpillerViewModel.State.Progress.Empty
import presentation.PointingSpillerViewModel.State.Progress.Loading
import presentation.PointingSpillerViewModel.State.Success
import usecase.PointingPokerUseCase

class PointingSpillerViewModelTest {
    @RelaxedMockK
    private lateinit var pointingPokerUseCase: PointingPokerUseCase
    private lateinit var viewModel: PointingSpillerViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = PointingSpillerViewModel(pointingPokerUseCase)
    }

    @Test
    fun `given succeeded load pointing poker API when load API URL then should update state to success`() = runTest {
        val pointingPoker = newPointingPoker()
        coEvery { pointingPokerUseCase.invoke(API_URL) } returns pointingPoker

        viewModel.state.test {
            viewModel.loadFromApi(API_URL)

            assertEquals(Initial, awaitItem())
            assertEquals(Success(pointingPoker), awaitItem())
        }
    }

    @Test
    fun `given succeeded load pointing poker API when load API URL then should update progress state to empty`() = runTest {
        val pointingPoker = newPointingPoker()
        coEvery { pointingPokerUseCase.invoke(API_URL) } returns pointingPoker

        viewModel.errorState.test {
            viewModel.loadFromApi(API_URL)

            assertEquals(Empty, awaitItem())
            assertEquals(Loading, awaitItem())
            assertEquals(Empty, awaitItem())
        }
    }

    @Test
    fun `given failed load pointing poker API when load API URL then error state should not empty`() = runBlocking {
        val error = "Failed to load!!"
        coEvery { pointingPokerUseCase.invoke(API_URL) } throws Exception(error)

        viewModel.errorState.test {
            viewModel.loadFromApi(API_URL)

            assertEquals(Empty, awaitItem())
            assertEquals(Loading, awaitItem())
            assertEquals(Progress.Failure(error), awaitItem())
        }
    }

    companion object {
        const val API_URL = "https://fake-api.pointingpoker.com/fake/session/jri3c03r9fj"
    }
}