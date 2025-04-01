package api.cards.domain.usecase.impl

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.entity.vo.Cliente
import api.cards.domain.exception.CreditAnalysisException
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.domain.strategy.impl.CardsStrategyImpl
import api.cards.domain.usecase.CardsOffersUseCase
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import java.util.*

class CardsOffersUseCaseImpl(private val cardsOffersProcessorProvider: CardsOffersProcessorProvider) : CardsOffersUseCase {

    override fun getCardsOffers(retrieveClienteDataParams: RetrieveClienteDataParams): CardsOffers {

        return cardsOffersProcessorProvider.getCardsOffers(retrieveClienteDataParams)
    }

    override fun buildCardsOffers(cliente: Cliente, majority: Int, protocol: String, createdAt: String) {

        try {
            val dataNascimento = LocalDate.parse(cliente.data_nascimento)

            if (cliente.idade < majority) {
                throw CreditAnalysisException("A idade precisa ser maior ou igual a $majority", protocol)
            }

            val cardsOffersList = CardsStrategyImpl.getCardsOffers(cliente, dataNascimento)

            val cardsOffers = CardsOffers(
                    UUID.fromString(protocol),
                    createdAt,
                    cliente,
                    cardsOffersList
            )

            cardsOffersProcessorProvider.setCardsOffers(cardsOffers, protocol)
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("Data de nascimento inválida: ${e.message}")
        } catch (e: CreditAnalysisException) {
            throw CreditAnalysisException(e.message.toString(), protocol)
        }
    }
}