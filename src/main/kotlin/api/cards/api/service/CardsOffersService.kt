package api.cards.api.service

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams

interface CardsOffersService {
    fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers
}