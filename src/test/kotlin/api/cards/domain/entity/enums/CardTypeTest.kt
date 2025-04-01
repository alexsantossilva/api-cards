package api.cards.domain.entity.enums

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardTypeTest {

    @Test
    fun `test CARTAO_SEM_ANUIDADE getLimite`() {
        val cardType = CardType.CARTAO_SEM_ANUIDADE
        assertEquals(1000.00, cardType.getLimite(), 0.0)
    }

    @Test
    fun `test CARTAO_DE_PARCEIROS getLimite`() {
        val cardType = CardType.CARTAO_DE_PARCEIROS
        assertEquals(3000.00, cardType.getLimite(), 0.0)
    }

    @Test
    fun `test CARTAO_COM_CASHBACK getLimite`() {
        val cardType = CardType.CARTAO_COM_CASHBACK
        assertEquals(5000.00, cardType.getLimite(), 0.0)
    }
}