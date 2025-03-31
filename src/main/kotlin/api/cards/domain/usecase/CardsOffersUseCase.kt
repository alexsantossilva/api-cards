package api.cards.domain.usecase

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.entity.vo.Cliente

interface CardsOffersUseCase {

    fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers

    fun buildCardsOffers(cliente: Cliente, majority: Int, protocol: String, createdAt: String)
}