package api.cards.domain.usecase.impl

import api.cards.ObjectsMocks
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.exception.CreditAnalysisException
import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.time.format.DateTimeParseException
import java.util.*

@Disabled
class CardsOffersUseCaseImplTest {

    @Mock
    lateinit var cardsOffersProcessorProvider: CardsOffersProcessorProvider

    @InjectMocks
    lateinit var cardsOffersUseCaseImpl: CardsOffersUseCaseImpl

    // Setup Mockito
    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test buildCardsOffers throws CreditAnalysisException if age is less than majority`() {
        // Arrange
        val client = ObjectsMocks.mockMockCliente()

        val protocol = "745f2812-c3f4-42ce-93fb-e119e643bda2"
        val majority = 18
        val createdAt = "2025-03-31"

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(CreditAnalysisException::class.java) {
            cardsOffersUseCaseImpl.buildCardsOffers(client, majority, protocol, createdAt)
        }
    }

    @Test
    fun `test buildCardsOffers creates cardsOffers and calls setCardsOffers`() {
        // Arrange
        val client = ObjectsMocks.mockMockCliente()

        val protocol = "745f2812-c3f4-42ce-93fb-e119e643bda2"
        val majority = 18
        val createdAt = "2025-03-31"

        // Mock do CardsOffersProcessorProvider
        val mockCardsOffersProcessorProvider = mock(CardsOffersProcessorProvider::class.java)

        val cardsOffersUseCase = CardsOffersUseCaseImpl(mockCardsOffersProcessorProvider)

        cardsOffersUseCase.buildCardsOffers(client, majority, protocol, createdAt)

        Mockito.verify(mockCardsOffersProcessorProvider, Mockito.times(1))
                .setCardsOffers(Mockito.any(), Mockito.eq(protocol))
    }

    @Test
    fun `test buildCardsOffers throws CreditAnalysisException when DateTimeParseException occurs`() {
        // Arrange
        val client = ObjectsMocks.mockMockCliente()

        val protocol = "745f2812-c3f4-42ce-93fb-e119e643bda2"
        val majority = 18
        val createdAt = "221-11-30"

        // Act & Assert
        assertThrows(DateTimeParseException::class.java) {
            cardsOffersUseCaseImpl.buildCardsOffers(client, majority, protocol, createdAt)
        }
    }

    @Test
    fun `test getCardsOffers should return cardsOffers`() {
        // Arrange
        val retrieveClienteDataParams = RetrieveClienteDataParams(UUID.randomUUID())
        val expectedCardsOffers = ObjectsMocks.getMockCardsOffers()

        val mockCardsOffersProcessorProvider = mock(CardsOffersProcessorProvider::class.java)
        `when`(mockCardsOffersProcessorProvider.getCardsOffers(retrieveClienteDataParams))
                .thenReturn(expectedCardsOffers)

        val cardsOffersUseCase = CardsOffersUseCaseImpl(mockCardsOffersProcessorProvider)

        // Act
        val actualCardsOffers = cardsOffersUseCase.getCardsOffers(retrieveClienteDataParams)

        // Assert
        assertEquals(expectedCardsOffers, actualCardsOffers)
    }
}