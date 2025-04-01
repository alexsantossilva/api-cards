package api.cards.domain.usecase.impl

import api.cards.ObjectsMocks
import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.entity.vo.Cliente
import api.cards.domain.exception.CreditAnalysisException
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.domain.strategy.impl.CardsStrategyImpl
import api.cards.domain.usecase.CardsOffersUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class CardsOffersUseCaseImplTest {

    private lateinit var cardsOffersProcessorProvider: CardsOffersProcessorProvider
    private lateinit var cardsOffersUseCase: CardsOffersUseCase

    @BeforeEach
    fun setUp() {
        cardsOffersProcessorProvider = mock(CardsOffersProcessorProvider::class.java)
        cardsOffersUseCase = CardsOffersUseCaseImpl(cardsOffersProcessorProvider)
    }

    @Test
    fun `test getCardsOffers`() {
        val retrieveClienteDataParams = RetrieveClienteDataParams(/* initialize with necessary parameters */)
        val expectedCardsOffers = ObjectsMocks.getMockCardsOffers()

        `when`(cardsOffersProcessorProvider.getCardsOffers(retrieveClienteDataParams)).thenReturn(expectedCardsOffers)

        val result = cardsOffersUseCase.getCardsOffers(retrieveClienteDataParams)

        assertEquals(expectedCardsOffers, result)
        verify(cardsOffersProcessorProvider, times(1)).getCardsOffers(retrieveClienteDataParams)
    }

    @Test
    fun `test buildCardsOffers with valid data`() {
        val cliente = Cliente(
            "Cliente Teste",
            "123.456.789-10",
            25,
            "2000-01-01",
            "SP",
            2000.00,
            "cliente@teste.com",
            "11999992020"
        )
        val majority = 18
        val protocol = UUID.randomUUID().toString()
        val createdAt = LocalDateTime.now().toString()

        val cardsOffersList = listOf(ObjectsMocks.mockCartao())

        cardsOffersUseCase.buildCardsOffers(cliente, majority, protocol, createdAt)

        val expectedCardsOffers = CardsOffers(UUID.fromString(protocol), createdAt, cliente, cardsOffersList)
        verify(cardsOffersProcessorProvider, times(1)).setCardsOffers(expectedCardsOffers, protocol)
    }

    @Test
    fun `test buildCardsOffers with underage client`() {
        val cliente = Cliente(
            "Cliente Teste",
            "123.456.789-10",
            16,
            "1994-01-01",
            "SP",
            5000.00,
            "cliente@teste.com",
            "11999992020"
        )
        val majority = 18
        val protocol = UUID.randomUUID().toString()
        val createdAt = LocalDateTime.now().toString()

        val exception = assertThrows(CreditAnalysisException::class.java) {
            cardsOffersUseCase.buildCardsOffers(cliente, majority, protocol, createdAt)
        }

        assertEquals("A idade precisa ser maior ou igual a 18", exception.message)
    }

    @Test
    fun `test buildCardsOffers with invalid date`() {
        val cliente = Cliente(
            "Cliente Teste",
            "123.456.789-10",
            20,
            "invalid-date",
            "SP",
            5000.00,
            "cliente@teste.com",
            "11999992020"
        )
        val majority = 18
        val protocol = UUID.randomUUID().toString()
        val createdAt = LocalDate.now().toString()

        val exception = assertThrows(IllegalArgumentException::class.java) {
            cardsOffersUseCase.buildCardsOffers(cliente, majority, protocol, createdAt)
        }

        assertEquals("Data de nascimento inválida: Text 'invalid-date' could not be parsed at index 0", exception.message)

    }
}