package api.client.domain.usecase.impl

import api.client.domain.entity.CardsOffers
import api.client.domain.entity.RetrieveClienteDataParams
import api.client.domain.ports.processor.CardsOffersProcessorProvider
import api.client.domain.usecase.CardsOffersUseCase

class CardsOffersUseCaseImpl(private val cardsOffersProcessorProvider: CardsOffersProcessorProvider) : CardsOffersUseCase {

    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {
        TODO("Not yet implemented")
    }
}