package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import common.ComposeId
import common.setId
import domain.PointingPoker
import domain.PointingPoker.AveragePoint.PointCounter

@Composable
fun AveragePoint(
    averagePoint: PointingPoker.AveragePoint,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row {
            Text("Point", modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
            Text("Count", modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
        }
        averagePoint.pointCounter.forEachIndexed { index, pointCounter ->
            PointCounter(pointCounter, isHighest = index == 0)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Average ${averagePoint.average}",
            modifier = Modifier.setId(ComposeId.AVERAGE_POINT),
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
    }
}

@Composable
private fun PointCounter(pointCounter: PointCounter, isHighest: Boolean = false) {
    Row(modifier = Modifier.setId(ComposeId.POINT_COUNTER)) {
        val fontWeight = if (isHighest) FontWeight.SemiBold else FontWeight.Normal
        val fontColor = if (isHighest) Color.Red else Color.Black
        Text(
            pointCounter.point.toString(),
            modifier = Modifier.weight(1f),
            fontWeight = fontWeight,
            color = fontColor
        )
        Text(
            pointCounter.count.toString(),
            modifier = Modifier.weight(1f),
            fontWeight = fontWeight,
            color = fontColor
        )
    }
}