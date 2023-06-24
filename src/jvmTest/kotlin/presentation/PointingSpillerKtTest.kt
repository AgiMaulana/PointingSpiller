package presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onFirst
import common.ComposeId
import datafactories.newAveragePoint
import datafactories.newObserver
import datafactories.newPlayer
import datafactories.newPointCounter
import datafactories.newPointingPoker
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import presentation.PointingSpillerViewModel.State
import presentation.PointingSpillerViewModel.State.Progress
import testutils.onAllNodesWithId
import testutils.onNodeWithId

class PointingSpillerKtTest {

    private lateinit var stateFlow: MutableStateFlow<State>
    private lateinit var progressStateFlow: MutableStateFlow<Progress>
    @RelaxedMockK
    private lateinit var viewModel: PointingSpillerViewModel

    @get:Rule
    val compose = createComposeRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        stateFlow = MutableStateFlow(State.Initial)
        progressStateFlow = MutableStateFlow(Progress.Empty)
        every { viewModel.state } returns stateFlow
        every { viewModel.errorState } returns progressStateFlow
    }

    @Test
    fun `when progress state is loading then show loading indicator`() {
        compose.setContent {
            PointingSpiller(viewModel)
        }

        progressStateFlow.update { Progress.Loading }
        compose.waitForIdle()

        compose.onNodeWithId(ComposeId.LOADING_INDICATOR)
            .assertIsDisplayed()
    }

    @Test
    fun `when progress state is fail then show error text`() {
        val error = "Failed to load, no internet connection."
        compose.setContent {
            PointingSpiller(viewModel)
        }

        progressStateFlow.update { Progress.Failure(error) }
        compose.waitForIdle()

        compose.onNodeWithId(ComposeId.ERROR_TEXT)
            .assertIsDisplayed()
            .assertTextEquals(error)
    }

    @Test
    fun `when progress state is empty then do not display error text and loading indicator`() {
        compose.setContent {
            PointingSpiller(viewModel)
        }

        progressStateFlow.update { Progress.Empty }
        compose.waitForIdle()

        compose.onNodeWithId(ComposeId.ERROR_TEXT)
            .assertDoesNotExist()
        compose.onNodeWithId(ComposeId.LOADING_INDICATOR)
            .assertDoesNotExist()
    }

    @Test
    fun `when state is success then should show pointing poker data`() {
        val player = newPlayer(
            withName = "Steve",
            withPoint = 943
        )
        val observer = newObserver(withName = "Andrew")
        val pointCounter = newPointCounter(
            withPoint = 2,
            withCount = 10
        )
        val averagePoint = newAveragePoint(
            withPointCounter = listOf(pointCounter),
            withAverage = 2.5
        )
        val pointingPoker = newPointingPoker(
            withPlayers = listOf(player),
            withObservers = listOf(observer),
            withAveragePoint = averagePoint
        )
        compose.setContent {
            PointingSpiller(viewModel)
        }

        stateFlow.update {
            State.Success(pointingPoker)
        }
        compose.waitForIdle()

        // player
        compose.onAllNodesWithId(ComposeId.PLAYER)
            .onFirst()
            .onChildAt(0)
            .assertTextEquals(player.name)
            .assertIsDisplayed()
        compose.onAllNodesWithId(ComposeId.PLAYER)
            .onFirst()
            .onChildAt(1)
            .assertTextEquals(player.point.toString())
            .assertIsDisplayed()
        // observer
        compose.onAllNodesWithId(ComposeId.OBSERVER)
            .onFirst()
            .assertTextEquals(observer.name)
            .assertIsDisplayed()
        // average point
        compose.onAllNodesWithId(ComposeId.POINT_COUNTER)
            .onFirst()
            .onChildAt(0)
            .assertTextEquals(pointCounter.point.toString())
            .assertIsDisplayed()
        compose.onAllNodesWithId(ComposeId.POINT_COUNTER)
            .onFirst()
            .onChildAt(1)
            .assertTextEquals(pointCounter.count.toString())
            .assertIsDisplayed()
        compose.onNodeWithId(ComposeId.AVERAGE_POINT)
            .assertTextEquals("Average ${averagePoint.average}")
            .assertIsDisplayed()
    }

    companion object {
        const val API_URL = "https://fake-api.pointingpoker.com/session/12345"
    }
}

