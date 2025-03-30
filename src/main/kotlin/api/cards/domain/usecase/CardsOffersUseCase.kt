package api.client.domain.usecase

import api.client.domain.entity.CardsOffers
import api.client.domain.entity.RetrieveClienteDataParams

interface CardsOffersUseCase {

    fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers
}