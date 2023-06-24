package usecase

import domain.PointingPoker
import gateway.PointingApiGateway

interface PointingPokerUseCase {
    suspend operator fun invoke(url: String): PointingPoker
}

class PointingPokerUseCaseImpl(
    private val pointingApiGateway: PointingApiGateway
) : PointingPokerUseCase {

    override suspend fun invoke(url: String): PointingPoker {
        return pointingApiGateway.getSession(url)
    }

}
