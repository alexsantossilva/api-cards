package api.cards.infra.provider.processor

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.infra.db.Db

class ProcessorProvider(
        private val db: Db): CardsOffersProcessorProvider {
    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {
        TODO("Not yet implemented")
    }

    override fun setCardsOffers(cardsOffers: CardsOffers) {
        TODO("Not yet implemented")
    }
}