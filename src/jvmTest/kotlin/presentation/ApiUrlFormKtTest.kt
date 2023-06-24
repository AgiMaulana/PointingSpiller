package presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import common.ComposeId.BUTTON
import common.ComposeId.TEXT_FIELD
import org.junit.Rule
import org.junit.Test
import testutils.onNodeWithId
import kotlin.test.assertEquals

class ApiUrlFormKtTest  {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun `when enter button clicked then on change url invoked`() {
        var apiUrl = ""
        val enteredApiUrl = "https://fake-api-url.com/1234"

        compose.setContent {
            ApiUrlForm {
                apiUrl = it
            }
        }
        compose.onNodeWithId(TEXT_FIELD)
            .performTextInput(enteredApiUrl)
        compose.onNodeWithId(BUTTON)
            .performClick()

        assertEquals(enteredApiUrl, apiUrl)
    }
}
