package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import common.ComposeId
import common.setId
import domain.PointingPoker

@Composable
fun PlayerList(
    players: List<PointingPoker.Player>,
    modifier: Modifier = Modifier
) {
    Row {
        Text("Player", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        Text("Point", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
    }
    Column(modifier = modifier) {
        players.forEach { player ->
            Player(player, modifier = Modifier.setId(ComposeId.PLAYER))
        }
    }
}

@Composable
private fun Player(
    player: PointingPoker.Player,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Text(
            text = player.name,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = player.point.toString(),
            modifier = Modifier.weight(1f)
        )
    }
}