package api.cards.infra.provider.processor

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.infra.db.Db
import api.cards.infra.mapper.CardsOffersMapper
import com.google.gson.Gson

class ProcessorProvider(
        private val db: Db): CardsOffersProcessorProvider {
    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {
        val result = db.getValue(retrieveClienteDataParams.id.toString())
        if (result == "NULL") {

        }

        return CardsOffersMapper.toCardsOffers(result.toString())
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