package presentation

import domain.PointingPoker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.PointingSpillerViewModel.State.Progress
import usecase.PointingPokerUseCase

class PointingSpillerViewModel(
    private val pointingPokerUseCase: PointingPokerUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Initial)
    val state = _state.asStateFlow()
    private val _progressState: MutableStateFlow<Progress> = MutableStateFlow(Progress.Empty)
    val errorState = _progressState.asStateFlow()

    fun loadFromApi(url: String) {
        viewModelScope.launch {
            _progressState.update { Progress.Loading }
            try {
                val pointingPoker = pointingPokerUseCase(url)
                _state.update { State.Success(pointingPoker) }
                _progressState.update { Progress.Empty }
            } catch (e: Exception) {
                _progressState.update { Progress.Failure(e.message ?: "Unkown") }
            }
        }
    }

    sealed interface State {
        object Initial : State
        data class Success(val pointingPoker: PointingPoker) : State

        sealed interface Progress {
            object Empty: Progress
            object Loading: Progress
            data class Failure(val error: String): Progress
        }
    }
}