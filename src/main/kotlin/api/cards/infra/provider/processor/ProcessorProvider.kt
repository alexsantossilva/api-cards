package api.cards.infra.provider.processor

import api.cards.api.exception.ErrorResponseException
import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.exception.CardsOffersNotFoundException
import api.cards.domain.exception.CreditAnalysisException
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.infra.db.Db
import api.cards.infra.mapper.CardsOffersMapper
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.google.gson.Gson

class ProcessorProvider(
        private val db: Db): CardsOffersProcessorProvider {
    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {
        val result = db.getValue(retrieveClienteDataParams.id.toString())

        try {

            if (result == null) {
                throw CardsOffersNotFoundException("No Cards Offers")
            }

            val cardsOffers = CardsOffersMapper.toCardsOffers(result.toString())
            if (cardsOffers.cartoes_ofertados.isEmpty()) {
                throw CardsOffersNotFoundException("No Cards Offers")
            }

            return cardsOffers
        } catch (e: CreditAnalysisException) {
            throw CreditAnalysisException(e.message.toString(), retrieveClienteDataParams.id.toString())
        } catch (e: JsonProcessingException) {
            throw ErrorResponseException(result.toString())
        } catch (e: JsonMappingException) {
            throw ErrorResponseException(result.toString())
        }
    }

    override fun setCardsOffers(cardsOffers: CardsOffers, protocol: String) {
        db.saveValue(protocol, toJson(cardsOffers))
    }

    private fun toJson(cardsOffers: CardsOffers): String {
        val gson = Gson()
        return gson.toJson(cardsOffers)
    }
}