package api.cards

import api.cards.domain.entity.CardsOffers
import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.entity.vo.Cliente
import java.util.*

object ObjectsMocks {

    fun getMockCardsOffers(): CardsOffers {
        return CardsOffers(
                UUID.randomUUID(),
                "2024-03-31T12:00:00.000",
                Cliente(
                        "Cliente Teste",
                        "123.456.789-10",
                        30,
                        "1994-01-01",
                        "SP",
                        5000.00,
                        "cliente@teste.com",
                        "11999992020"
                ),
                listOf(
                        CartoesOfertados(
                                "Cartão Sem Anuidade",
                                5000.00,
                                0.00,
                                "APROVADO"
                        ),
                        CartoesOfertados(
                                "Cartão Cashback",
                                3000.00,
                                2.5,
                                "APROVADO"
                        )
                )
        )
    }

    fun mockMockCliente() = Cliente(
           "Cliente Teste",
            "123.456.789-10",
            30,
            "1994-01-01",
            "SP",
            5000.00,
            "cliente@teste.com",
            "11999992020"
    )

    fun mockCartao() = CartoesOfertados(
            "Cartão Sem Anuidade",
            0.00,
            5000.00,
            "APROVADO"
    )
}