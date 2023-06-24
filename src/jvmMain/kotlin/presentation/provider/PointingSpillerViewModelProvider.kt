package presentation.provider

import presentation.PointingSpillerViewModel
import provider.ProviderFactory
import usecase.PointingPokerUseCase
import usecase.providers.PointingPokerUseCaseProvider

class PointingSpillerViewModelProvider(
    private val pointingPokerUseCase: PointingPokerUseCase = PointingPokerUseCaseProvider().get()
) : ProviderFactory<PointingSpillerViewModel> {
    override fun get(): PointingSpillerViewModel {
        return PointingSpillerViewModel(pointingPokerUseCase)
    }
}