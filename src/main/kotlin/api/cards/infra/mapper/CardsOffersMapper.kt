package api.cards.infra.mapper

import api.cards.domain.entity.CardsOffers
import api.cards.infra.dto.ClienteResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object CardsOffersMapper {

    fun toClienteResponse(json: String): ClienteResponse {
        val objectMapper = jacksonObjectMapper()
        return objectMapper.readValue(json, ClienteResponse::class.java)
    }

    fun toCardsOffers(json: String): CardsOffers {
        val objectMapper = jacksonObjectMapper()
        return objectMapper.readValue(json, CardsOffers::class.java)
    }
}