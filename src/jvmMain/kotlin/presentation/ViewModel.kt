package presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

expect abstract class ViewModel() {
    val viewModelScope: CoroutineScope
    open fun onCleared()
}

actual abstract class ViewModel actual constructor() {
    private val job = Job()
    private val scope = CoroutineScope(job)
    actual val viewModelScope: CoroutineScope
        get() = scope

    actual open fun onCleared() {
        scope.cancel()
    }
}