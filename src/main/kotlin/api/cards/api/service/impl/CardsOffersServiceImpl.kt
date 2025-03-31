package api.cards.api.service.impl

import api.cards.api.service.CardsOffersService
import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.usecase.CardsOffersUseCase
import org.springframework.stereotype.Service

@Service
class CardsOffersServiceImpl(private val cardsOffersUseCase: CardsOffersUseCase) : CardsOffersService {
    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {
        return cardsOffersUseCase.getCardsOffers(retrieveClienteDataParams)
    }
}