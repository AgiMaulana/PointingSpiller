package api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointingPokerResponse (
//    @SerialName("AllPlayerVoted")
//    val allPlayerVoted: Boolean,
//    @SerialName("CurrentTime")
//    val currentTime: String,
//    @SerialName("LastVotesClearedTime")
//    val lastVotesClearedTime: String,
//    @SerialName("Options")
//    val options: Options,
//    @SerialName("PlayerActivity")
//    val playerActivity: List<String>,
    @SerialName("Players")
    val players: List<Player>,
//    @SerialName("SessionId")
//    val sessionId: Int,
//    @SerialName("SessionStats")
//    val sessionStats: SessionStats,
//    @SerialName("SessionUniqueId")
//    val sessionUniqueId: String,
//    @SerialName("StartTime")
//    val startTime: String,
//    @SerialName("TurnArchive")
//    val turnArchive: List<TurnArchive>
) {
    @Serializable
    data class Options(
        @SerialName("ObserverCanResetVotes")
        val observerCanResetVotes: Boolean,
        @SerialName("ObserverCanShowVotes")
        val observerCanShowVotes: Boolean,
        @SerialName("PlayerCanResetVotes")
        val playerCanResetVotes: Boolean,
        @SerialName("PlayerCanShowVotes")
        val playerCanShowVotes: Boolean,
        @SerialName("PointValues")
        val pointValues: List<PointValue>,
        @SerialName("ShowHistory")
        val showHistory: Boolean,
        @SerialName("ShowStoryDescription")
        val showStoryDescription: Boolean
    ) {
        @Serializable
        data class PointValue(
            @SerialName("Display")
            val display: String,
            @SerialName("Value")
            val value: String
        )
    }

    @Serializable
    data class Player(
        @SerialName("IsObserver")
        val isObserver: Boolean,
        @SerialName("LastActivity")
        val lastActivity: String,
        @SerialName("Name")
        val name: String,
        @SerialName("PlayerId")
        val playerId: String,
        @SerialName("Points")
        val points: String,
        @SerialName("TimeJoined")
        val timeJoined: String
    )

    @Serializable
    data class SessionStats(
        @SerialName("AveragePointValue")
        val averagePointValue: Int,
        @SerialName("PointVotes")
        val pointVotes: List<PointVote>,
        @SerialName("SecondsElapsed")
        val secondsElapsed: Int
    ) {
        @Serializable
        data class PointVote(
            @SerialName("Points")
            val points: String,
            @SerialName("Votes")
            val votes: Int
        )
    }

    @Serializable
    data class TurnArchive(
        @SerialName("ArchiveTime")
        val archiveTime: String,
        @SerialName("Id")
        val id: String,
        @SerialName("Players")
        val players: List<Player>,
        @SerialName("SessionStats")
        val sessionStats: SessionStats,
    ) {
        @Serializable
        data class Player(
            @SerialName("IsObserver")
            val isObserver: Boolean,
            @SerialName("LastActivity")
            val lastActivity: String,
            @SerialName("Name")
            val name: String,
            @SerialName("PlayerId")
            val playerId: String,
            @SerialName("Points")
            val points: String,
            @SerialName("TimeJoined")
            val timeJoined: String
        )

        @Serializable
        data class SessionStats(
            @SerialName("AveragePointValue")
            val averagePointValue: Double,
            @SerialName("PointVotes")
            val pointVotes: List<PointVote>,
            @SerialName("SecondsElapsed")
            val secondsElapsed: Int
        ) {
            @Serializable
            data class PointVote(
                @SerialName("Points")
                val points: String,
                @SerialName("Votes")
                val votes: Int
            )
        }
    }
}