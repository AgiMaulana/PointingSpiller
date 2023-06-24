package gateway.provider

import api.PointingPokerApi
import api.providers.ApiProviderFactory
import gateway.PointingApiGateway
import gateway.PointingApiGatewayImpl
import provider.ProviderFactory

class PointingApiGatewayProvider(
    private val api: PointingPokerApi = ApiProviderFactory().get()
) : ProviderFactory<PointingApiGateway> {
    override fun get(): PointingApiGateway {
        return PointingApiGatewayImpl(api)
    }
}