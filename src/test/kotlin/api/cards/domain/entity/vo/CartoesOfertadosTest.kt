package api.cards.domain.entity.vo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CartoesOfertadosTest {

    @Test
    fun `test CartoesOfertados creation`() {
        val tipoCartao = "CARTAO_COM_CASHBACK"
        val valorAnuidadeMensal = 20.0
        val valorLimiteDisponivel = 5000.0
        val status = "APROVADO"

        val cartoesOfertados = CartoesOfertados(
            tipo_cartao = tipoCartao,
            valor_anuidade_mensal = valorAnuidadeMensal,
            valor_limite_disponivel = valorLimiteDisponivel,
            status = status
        )

        assertNotNull(cartoesOfertados)
        assertEquals(tipoCartao, cartoesOfertados.tipo_cartao)
        assertEquals(valorAnuidadeMensal, cartoesOfertados.valor_anuidade_mensal, 0.0)
        assertEquals(valorLimiteDisponivel, cartoesOfertados.valor_limite_disponivel, 0.0)
        assertEquals(status, cartoesOfertados.status)
    }

    @Test
    fun `test CartoesOfertados equality`() {
        val tipoCartao = "CARTAO_COM_CASHBACK"
        val valorAnuidadeMensal = 20.0
        val valorLimiteDisponivel = 5000.0
        val status = "APROVADO"

        val cartoesOfertados1 = CartoesOfertados(
            tipo_cartao = tipoCartao,
            valor_anuidade_mensal = valorAnuidadeMensal,
            valor_limite_disponivel = valorLimiteDisponivel,
            status = status
        )

        val cartoesOfertados2 = CartoesOfertados(
            tipo_cartao = tipoCartao,
            valor_anuidade_mensal = valorAnuidadeMensal,
            valor_limite_disponivel = valorLimiteDisponivel,
            status = status
        )

        assertEquals(cartoesOfertados1, cartoesOfertados2)
    }

    @Test
    fun `test CartoesOfertados copy`() {
        val tipoCartao = "CARTAO_COM_CASHBACK"
        val valorAnuidadeMensal = 20.0
        val valorLimiteDisponivel = 5000.0
        val status = "APROVADO"

        val cartoesOfertados = CartoesOfertados(
            tipo_cartao = tipoCartao,
            valor_anuidade_mensal = valorAnuidadeMensal,
            valor_limite_disponivel = valorLimiteDisponivel,
            status = status
        )

        val copiedCartoesOfertados = cartoesOfertados.copy(status = "REJEITADO")

        assertNotEquals(cartoesOfertados, copiedCartoesOfertados)
        assertEquals("REJEITADO", copiedCartoesOfertados.status)
        assertEquals(cartoesOfertados.tipo_cartao, copiedCartoesOfertados.tipo_cartao)
        assertEquals(cartoesOfertados.valor_anuidade_mensal, copiedCartoesOfertados.valor_anuidade_mensal, 0.0)
        assertEquals(cartoesOfertados.valor_limite_disponivel, copiedCartoesOfertados.valor_limite_disponivel, 0.0)
    }
}