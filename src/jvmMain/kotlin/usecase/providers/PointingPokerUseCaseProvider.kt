package usecase.providers

import gateway.PointingApiGateway
import gateway.provider.PointingApiGatewayProvider
import provider.ProviderFactory
import usecase.PointingPokerUseCase
import usecase.PointingPokerUseCaseImpl

class PointingPokerUseCaseProvider(
    private val pointingApiGateway: PointingApiGateway = PointingApiGatewayProvider().get()
) : ProviderFactory<PointingPokerUseCase> {
    override fun get(): PointingPokerUseCase {
        return PointingPokerUseCaseImpl(pointingApiGateway)
    }
}