package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ComposeId.BUTTON
import common.ComposeId.TEXT_FIELD
import common.setId


@Composable
fun ApiUrlForm(
    modifier: Modifier = Modifier,
    onUrlChange: (String) -> Unit
) {
    var apiUrl by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .setId(TEXT_FIELD),
            value = apiUrl,
            onValueChange = { text ->
                apiUrl = text
            },
            label = { Text(text = "Pointing Poker API URL") },
            placeholder = { Text(text = "Enter Pointing Poker API URL") },
        )

        Spacer(
            modifier = Modifier.height(8.dp)
                .fillMaxWidth()
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .setId(BUTTON),
            onClick = {
                onUrlChange(apiUrl)
            }
        ) {
            Text("Enter")
        }
    }
}
