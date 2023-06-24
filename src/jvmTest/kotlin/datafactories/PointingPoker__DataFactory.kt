package datafactories

import domain.PointingPoker
import domain.PointingPoker.AveragePoint
import domain.PointingPoker.AveragePoint.PointCounter
import domain.PointingPoker.Observer
import domain.PointingPoker.Player

fun newPointingPoker(
    withPlayers: List<Player> = listOf(newPlayer()),
    withObservers: List<Observer> = listOf(newObserver()),
    withAveragePoint: AveragePoint = newAveragePoint()
) = PointingPoker(withPlayers, withObservers, withAveragePoint)

fun newPlayer(
    withName: String = "Bob",
    withPoint: Int = 2
) = Player(withName, withPoint)

fun newObserver(
    withName: String = "Alice"
) = Observer(withName)

fun newAveragePoint(
    withPointCounter: List<PointCounter> = listOf(newPointCounter()),
    withAverage: Double = 2.0
) = AveragePoint(withPointCounter, withAverage)

fun newPointCounter(
    withPoint: Int = 2,
    withCount: Int = 7
) = PointCounter(withPoint, withCount)