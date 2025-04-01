import api.cards.ObjectsMocks
import api.cards.api.service.impl.CardsOffersServiceImpl
import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.usecase.CardsOffersUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CardsOffersServiceImplTest {

    private val cardsOffersUseCase: CardsOffersUseCase = Mockito.mock(CardsOffersUseCase::class.java)
    private val service = CardsOffersServiceImpl(cardsOffersUseCase)

    @Test
    fun `deve retornar ofertas de cartao`() {
        // Given
        val retrieveClienteDataParams = RetrieveClienteDataParams(UUID.randomUUID())
        val expectedCardsOffers = CardsOffers(
                numero_solicitacao = UUID.randomUUID(),
                data_solicitacao = "2024-03-31T12:00:00.000",
                cliente = ObjectsMocks.mockMockCliente(),
                cartoes_ofertados = listOf(ObjectsMocks.mockCartao())
        )

        Mockito.`when`(cardsOffersUseCase.getCardsOffers(retrieveClienteDataParams))
                .thenReturn(expectedCardsOffers)

        // When
        val result = service.getCardsOffers(retrieveClienteDataParams)

        // Then
        assertEquals(expectedCardsOffers, result)
        Mockito.verify(cardsOffersUseCase).getCardsOffers(retrieveClienteDataParams)
    }
}