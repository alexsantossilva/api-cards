package api.client.domain.ports.processor

import api.client.domain.entity.CardsOffers
import api.client.domain.entity.RetrieveClienteDataParams

interface CardsOffersProcessorProvider {

    fun getClientData(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers
}