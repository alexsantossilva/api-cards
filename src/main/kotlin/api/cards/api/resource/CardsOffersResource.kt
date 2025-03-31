package api.cards.api.resource

import api.cards.api.service.impl.CardsOffersServiceImpl
import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/cartoes")
class CardsOffersResource(private val service: CardsOffersServiceImpl) {

    @GetMapping("/{id}")
    fun getCardsOffers(@PathVariable id: String): ResponseEntity<CardsOffers> {
        try {
            if (id.isEmpty()) {
                throw IllegalArgumentException("ID Null")
            }
            val uuid = UUID.fromString(id)
            val retrieveClienteDataParams = RetrieveClienteDataParams(
                uuid
            )
            val cardsOffers = service.getCardsOffers(retrieveClienteDataParams)

            return ResponseEntity.ok(cardsOffers)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(e.message)
        }
    }
}