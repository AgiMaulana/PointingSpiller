package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import common.ComposeId
import common.setId
import presentation.PointingSpillerViewModel.State
import presentation.PointingSpillerViewModel.State.Progress
import presentation.PointingSpillerViewModel.State.Progress.Failure
import presentation.PointingSpillerViewModel.State.Progress.Loading
import presentation.PointingSpillerViewModel.State.Success
import presentation.provider.PointingSpillerViewModelProvider

@Composable
fun PointingSpiller(
    viewModel: PointingSpillerViewModel = PointingSpillerViewModelProvider().get()
) {
    val state: State by viewModel.state.collectAsState()
    val progressState: Progress by viewModel.errorState.collectAsState()

    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.onCleared()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar {
                Text("Pointing Spiller")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ApiUrlForm(
                onUrlChange = {
                    viewModel.loadFromApi(it)
                }
            )

            Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
                when (val progress = progressState) {
                    is Loading -> {
                        Column(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.setId(ComposeId.LOADING_INDICATOR))
                        }
                    }
                    is Failure -> {
                        Text(
                            text = (progressState as Failure).error,
                            modifier = Modifier.setId(ComposeId.ERROR_TEXT)
                        )
                    }
                    else -> Box(modifier = Modifier.width(0.dp).height(0.dp)) {  }
                }

                when (val st = state) {
                    is Success -> {
                        Column {
                            PlayerList(st.pointingPoker.players)

                            Spacer(modifier = Modifier.height(16.dp))

                            Text("Observers", fontWeight = FontWeight.Bold)
                            ObserverList(st.pointingPoker.observers)

                            Spacer(modifier = Modifier.height(16.dp))

                            Text("Point Details", fontWeight = FontWeight.Bold)
                            AveragePoint(st.pointingPoker.averagePoint)
                        }
                    }
                    else -> Box(modifier = Modifier.width(0.dp).height(0.dp)) {  }
                }
            }
        }
    }
}
