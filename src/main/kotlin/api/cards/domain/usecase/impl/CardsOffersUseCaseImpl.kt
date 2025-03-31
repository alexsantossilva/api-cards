package api.cards.domain.usecase.impl

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.entity.vo.Cliente
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.domain.strategy.CardsOffersFactory
import api.cards.domain.usecase.CardsOffersUseCase
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class CardsOffersUseCaseImpl(private val cardsOffersProcessorProvider: CardsOffersProcessorProvider) : CardsOffersUseCase {

    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {

        return cardsOffersProcessorProvider.getCardsOffers(retrieveClienteDataParams)
    }

    override fun buildCardsOffers(cliente: Cliente, majority: Int, minimumWage: Double) {

//        cardsOffersProcessorProvider.setCardsOffers()
//        println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")
    }

    private fun validateMajority(dataNascimento: LocalDate, majority: Int): Boolean {
        val now = LocalDate.now()
        val age = ChronoUnit.YEARS.between(dataNascimento, now)
        return age >= majority
    }
}