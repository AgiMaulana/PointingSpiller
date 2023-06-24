package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ComposeId
import common.setId
import domain.PointingPoker

@Composable
fun ObserverList(
    observers: List<PointingPoker.Observer>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        observers.forEach { observer ->
            Text(
                observer.name,
                modifier = Modifier.setId(ComposeId.OBSERVER)
            )
        }
    }
}