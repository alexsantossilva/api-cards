package api.cards.domain.infra.provider.processor

import api.cards.ObjectsMocks
import api.cards.api.exception.ErrorResponseException
import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.RetrieveClienteDataParams
import api.cards.domain.exception.CardsOffersNotFoundException
import api.cards.domain.exception.CreditAnalysisException
import api.cards.infra.db.Db
import api.cards.infra.mapper.CardsOffersMapper
import api.cards.infra.provider.processor.ProcessorProvider
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProcessorProviderTest {

    @Mock
    private lateinit var db: Db

    @InjectMocks
    private lateinit var processorProvider: ProcessorProvider

    private lateinit var retrieveClienteDataParams: RetrieveClienteDataParams
    private lateinit var cardsOffers: CardsOffers

    @BeforeEach
    fun setUp() {
        retrieveClienteDataParams = RetrieveClienteDataParams(UUID.randomUUID())
        cardsOffers = CardsOffers(
            numero_solicitacao = UUID.randomUUID(),
            data_solicitacao = "2025-04-01",
            cliente = ObjectsMocks.mockCliente(),
            cartoes_ofertados = listOf(ObjectsMocks.mockCartao())
        )
    }

    @Test
    fun `test getCardsOffers success`() {
        `when`(db.getValue(retrieveClienteDataParams.id.toString())).thenReturn(Gson().toJson(cardsOffers))

        val result = processorProvider.getCardsOffers(retrieveClienteDataParams)

        assertNotNull(result)
        assertEquals(cardsOffers, result)
    }

    @Test
    fun `test getCardsOffers not found`() {
        `when`(db.getValue(retrieveClienteDataParams.id.toString())).thenReturn(null)

        val exception = assertThrows(CardsOffersNotFoundException::class.java) {
            processorProvider.getCardsOffers(retrieveClienteDataParams)
        }

        assertEquals("No Cards Offers", exception.message)
    }

    @Disabled
    @Test
    fun `test getCardsOffers credit analysis exception`() {
        `when`(db.getValue(retrieveClienteDataParams.id.toString())).thenReturn(Gson().toJson(cardsOffers))
        `when`(CardsOffersMapper.toCardsOffers(anyString())).thenThrow(CreditAnalysisException::class.java)

        val exception = assertThrows(CreditAnalysisException::class.java) {
            processorProvider.getCardsOffers(retrieveClienteDataParams)
        }

        assertNotNull(exception)
    }

    @Test
    fun `test getCardsOffers json processing exception`() {
        `when`(db.getValue(retrieveClienteDataParams.id.toString())).thenReturn("invalid-json")

        val exception = assertThrows(ErrorResponseException::class.java) {
            processorProvider.getCardsOffers(retrieveClienteDataParams)
        }

        assertNotNull(exception)
    }

    @Disabled
    @Test
    fun `test setCardsOffers`() {
        val protocol = UUID.randomUUID().toString()

        processorProvider.setCardsOffers(cardsOffers, protocol)

        verify(db, times(1)).saveValue(eq(protocol), anyString())
    }
}