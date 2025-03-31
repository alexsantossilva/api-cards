package api.cards.infra.provider.processor

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.infra.db.Db
import com.google.gson.Gson

class ProcessorProvider(
        private val db: Db): CardsOffersProcessorProvider {
    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {
        TODO("Not yet implemented")
    }

    override fun setCardsOffers(cardsOffers: CardsOffers?, protocol: String) {
        if (cardsOffers != null) {
            db.saveValue(protocol, toJson(cardsOffers))
        } else {
            db.saveValue("NULL", protocol)
        }
    }

    private fun toJson(cardsOffers: CardsOffers): String {
        val gson = Gson()
        return gson.toJson(cardsOffers)
    }
}