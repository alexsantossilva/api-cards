package api.cards.domain.ports.processor

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams

interface CardsOffersProcessorProvider {
    fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers

    fun setCardsOffers(cardsOffers: CardsOffers, protocol: String)
}