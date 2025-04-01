package api.cards.domain.entity

import api.cards.ObjectsMocks
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class CardsOffersTest {

    @Test
    fun `test CardsOffers creation`() {
        val cliente = ObjectsMocks.mockCliente()
        val cartoesOfertados = listOf(ObjectsMocks.mockCartao())
        val numeroSolicitacao = UUID.randomUUID()
        val dataSolicitacao = "2025-04-01"

        val cardsOffers = CardsOffers(
            numero_solicitacao = numeroSolicitacao,
            data_solicitacao = dataSolicitacao,
            cliente = cliente,
            cartoes_ofertados = cartoesOfertados
        )

        assertNotNull(cardsOffers)
        assertEquals(numeroSolicitacao, cardsOffers.numero_solicitacao)
        assertEquals(dataSolicitacao, cardsOffers.data_solicitacao)
        assertEquals(cliente, cardsOffers.cliente)
        assertEquals(cartoesOfertados, cardsOffers.cartoes_ofertados)
    }

    @Test
    fun `test CardsOffers equality`() {
        val cliente = ObjectsMocks.mockCliente()
        val cartoesOfertados = listOf(ObjectsMocks.mockCartao())
        val numeroSolicitacao = UUID.randomUUID()
        val dataSolicitacao = "2025-04-01"

        val cardsOffers1 = CardsOffers(
            numero_solicitacao = numeroSolicitacao,
            data_solicitacao = dataSolicitacao,
            cliente = cliente,
            cartoes_ofertados = cartoesOfertados
        )

        val cardsOffers2 = CardsOffers(
            numero_solicitacao = numeroSolicitacao,
            data_solicitacao = dataSolicitacao,
            cliente = cliente,
            cartoes_ofertados = cartoesOfertados
        )

        assertEquals(cardsOffers1, cardsOffers2)
    }

    @Test
    fun `test CardsOffers copy`() {
        val cliente = ObjectsMocks.mockCliente()
        val cartoesOfertados = listOf(ObjectsMocks.mockCartao())
        val numeroSolicitacao = UUID.randomUUID()
        val dataSolicitacao = "2025-04-01"

        val cardsOffers = CardsOffers(
            numero_solicitacao = numeroSolicitacao,
            data_solicitacao = dataSolicitacao,
            cliente = cliente,
            cartoes_ofertados = cartoesOfertados
        )

        val copiedCardsOffers = cardsOffers.copy(data_solicitacao = "2025-05-01")

        assertNotEquals(cardsOffers, copiedCardsOffers)
        assertEquals("2025-05-01", copiedCardsOffers.data_solicitacao)
        assertEquals(cardsOffers.numero_solicitacao, copiedCardsOffers.numero_solicitacao)
        assertEquals(cardsOffers.cliente, copiedCardsOffers.cliente)
        assertEquals(cardsOffers.cartoes_ofertados, copiedCardsOffers.cartoes_ofertados)
    }
}