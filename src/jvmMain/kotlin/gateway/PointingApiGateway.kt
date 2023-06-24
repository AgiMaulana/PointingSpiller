package gateway

import api.PointingPokerApi
import api.response.PointingPokerResponse
import domain.PointingPoker
import domain.PointingPoker.AveragePoint.PointCounter

interface PointingApiGateway {
    suspend fun getSession(url: String): PointingPoker
}

class PointingApiGatewayImpl(
    private val api: PointingPokerApi
) : PointingApiGateway {

    override suspend fun getSession(url: String): PointingPoker {
        val response = api.getSessions(url)
        return response.toPointingPoker()
    }

}

private fun PointingPokerResponse.toPointingPoker(): PointingPoker {
    val players = this.players
        .filter { it.isObserver.not() }
        .map {
        val point = it.points.ifBlank { "0" }.toInt()
        PointingPoker.Player(it.name, point)
    }
    val observers = this.players
        .filter { it.isObserver }
        .map { PointingPoker.Observer(it.name) }

    val pointCounter = players.filter { it.point > 0 }
        .map { PointCounter(it.point, players.count { p -> p.point == it.point }) }
        .distinctBy { it.point }
        .sortedByDescending { it.count }
    val average = (players.sumOf { it.point }.toDouble() / players.size.toDouble())

    return PointingPoker(
        players = players,
        observers = observers,
        PointingPoker.AveragePoint(pointCounter, average)
    )
}