package common

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

object ComposeId {
    const val TEXT_FIELD = "text_field"
    const val BUTTON = "button"
    const val ERROR_TEXT = "error_text"
    const val PLAYER = "player"
    const val OBSERVER = "observer"
    const val POINT_COUNTER = "POINT_COUNTER"
    const val AVERAGE_POINT = "AVERAGE_POINT"
    const val LOADING_INDICATOR = "AVERAGE_POINT"
}

fun Modifier.setId(id: String) = testTag(id)