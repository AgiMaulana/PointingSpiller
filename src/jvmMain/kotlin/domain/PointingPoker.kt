package domain

data class PointingPoker(
    val players: List<Player>,
    val observers: List<Observer>,
    val averagePoint: AveragePoint
) {
    data class Player(
        val name: String,
        val point: Int
    )

    data class Observer(val name: String)

    data class AveragePoint(
        val pointCounter: List<PointCounter>,
        val average: Double,
    ) {
        data class PointCounter(val point: Int, val count: Int)
    }
}
