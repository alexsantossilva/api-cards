package api.cards.api.exception

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ErrorDetailTest {

    @Test
    fun `should create ErrorDetail object correctly`() {
        // Arrange
        val app = "api-client"
        val tipoErro = "NOT_RULES"
        val mensagemInterna = "Tivemos um problema na sua análise, revise seus dados."

        // Act
        val errorDetail = ErrorDetail(app, tipoErro, mensagemInterna)

        // Assert
        assertNotNull(errorDetail)
        assertEquals(app, errorDetail.app)
        assertEquals(tipoErro, errorDetail.tipo_erro)
        assertEquals(mensagemInterna, errorDetail.mensagem_interna)
    }
}