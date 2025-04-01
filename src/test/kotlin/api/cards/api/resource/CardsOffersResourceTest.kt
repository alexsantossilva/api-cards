package api.cards.api.resource

import api.cards.ObjectsMocks
import api.cards.api.service.impl.CardsOffersServiceImpl
import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.entity.vo.Cliente
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

@ExtendWith(MockitoExtension::class)
class CardsOffersResourceTest {

    @Mock
    private lateinit var service: CardsOffersServiceImpl

    @InjectMocks
    private lateinit var resource: CardsOffersResource

    private lateinit var retrieveClienteDataParams: RetrieveClienteDataParams
    private lateinit var cardsOffers: CardsOffers

    @BeforeEach
    fun setUp() {
        retrieveClienteDataParams = RetrieveClienteDataParams(UUID.randomUUID())
        cardsOffers = ObjectsMocks.getMockCardsOffers()
    }

    @Test
    fun `deve retornar 200 e a oferta de cartão quando ID for válido`() {

        `when`(service.getCardsOffers(retrieveClienteDataParams)).thenReturn(cardsOffers)

        val response: ResponseEntity<CardsOffers> = resource.getCardsOffers(retrieveClienteDataParams.id.toString())

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(cardsOffers, response.body)
    }

    @Test
    fun `deve lançar exceção quando ID for inválido`() {
        val idInvalido = "id-invalido"

        val exception = assertThrows(IllegalArgumentException::class.java) {
            resource.getCardsOffers(idInvalido)
        }

        assertEquals("Invalid UUID string: $idInvalido", exception.message)
    }
}