package api.cards.domain.strategy.impl

import api.cards.domain.entity.enums.CardType
import api.cards.domain.entity.vo.Cliente
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CardsStrategyImplTest {

    @Test
    fun `test getCardsOffers when client is in SP and age is below 26 and renda_mensal above 5000`() {
        // Arrange
        val cliente = Cliente(
                "Cliente Teste",
                "123.456.798-10",
                26,
                "1998-10-01",
                "SP",
                6000.00,
                "cliente@teste.com",
                "11999999999",
        )

        val result = CardsStrategyImpl.getCardsOffers(cliente, LocalDate.parse(cliente.data_nascimento))

        assertEquals(3, result.size, "Espera-se 3 cartões")
    }

    @Test
    fun `test getCardsOffers when client is in SP and age is below 26 and renda_mensal between 1000 and 2000`() {
        // Arrange
        val cliente = Cliente(
                "Cliente Teste",
                "123.456.798-10",
                26,
                "1998-10-01",
                "SP",
                2000.00,
                "cliente@teste.com",
                "11999999999",
        )

        val result = CardsStrategyImpl.getCardsOffers(cliente, LocalDate.parse(cliente.data_nascimento))

        assertEquals(1, result.size, "Espera-se 1 cartão")
        assertEquals(CardType.CARTAO_SEM_ANUIDADE.name, result[0].tipo_cartao, "Sem Anuidade")
    }

    @Test
    fun `test getCardsOffers when client is not from RJ and age is below 20 and renda_mensal 1500`() {
        // Arrange
        val cliente = Cliente(
                "Cliente Teste",
                "123.456.798-10",
                26,
                "1998-10-01",
                "RJ",
                1500.00,
                "cliente@teste.com",
                "11999999999",
        )

        // Ação: Chamando o método
        val result = CardsStrategyImpl.getCardsOffers(cliente, LocalDate.parse(cliente.data_nascimento))

        assertEquals(1, result.size)
        assertEquals(CardType.CARTAO_SEM_ANUIDADE.name, result[0].tipo_cartao, "Sem Anuidade")
    }

    @Test
    fun `test getCardsOffers when client is above 30 years old`() {
        // Arrange
        val cliente = Cliente(
                "Cliente Teste",
                "123.456.798-10",
                45,
                "1998-10-01",
                "RJ",
                3000.00,
                "cliente@teste.com",
                "11999999999",
        )

        val result = CardsStrategyImpl.getCardsOffers(cliente, LocalDate.parse(cliente.data_nascimento))

        assertEquals(1, result.size, "Espera-se 2 cartões")
        assertEquals(CardType.CARTAO_DE_PARCEIROS.name, result[0].tipo_cartao, "Parceiros")
        assertEquals(CardType.CARTAO_SEM_ANUIDADE.name, result[1].tipo_cartao,"Sem Anuidade")
    }

    @Test
    fun `test getCardsOffers when client is above 25 and below 30 years old with renda_mensal above 5000`() {
        // Arrange
        val cliente = Cliente(
                "Cliente Teste",
                "123.456.798-10",
                30,
                "1998-10-01",
                "RJ",
                6000.00,
                "cliente@teste.com",
                "11999999999",
        )

        val result = CardsStrategyImpl.getCardsOffers(cliente, LocalDate.parse(cliente.data_nascimento))

        assertEquals(3, result.size)
    }

    @Test
    fun `test getCardsOffers when client is from SP above 25 and below 30 years old with renda_mensal 300`() {
        // Arrange
        val cliente = Cliente(
                "Cliente Teste",
                "123.456.798-10",
                30,
                "1998-10-01",
                "SP",
                300.00,
                "cliente@teste.com",
                "11999999999",
        )

        val result = CardsStrategyImpl.getCardsOffers(cliente, LocalDate.parse(cliente.data_nascimento))

        assertEquals(0, result.size)
    }

    @Test
    fun `test getCardsOffers when client is above 25 and below 30 years old with renda_mensal 300`() {
        // Arrange
        val cliente = Cliente(
                "Cliente Teste",
                "123.456.798-10",
                30,
                "1998-10-01",
                "RJ",
                300.00,
                "cliente@teste.com",
                "11999999999",
        )

        val result = CardsStrategyImpl.getCardsOffers(cliente, LocalDate.parse(cliente.data_nascimento))

        assertEquals(0, result.size)
    }
}