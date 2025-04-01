package api.cards.api.exception

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ErrorResponseTest {

    @Test
    fun `should create ErrorResponse object correctly`() {
        // Arrange
        val app = "api-client"
        val tipoErro = "NOT_RULES"
        val mensagemInterna = "Tivemos um problema na sua análise, revise seus dados."
        val errorDetail = ErrorDetail(app, tipoErro, mensagemInterna)

        val codigo = "422"
        val mensagem = "Dados insuficientes para análise"

        // Act
        val errorResponse = ErrorResponse(codigo, mensagem, errorDetail)

        // Assert
        assertNotNull(errorResponse)
        assertEquals(codigo, errorResponse.codigo)
        assertEquals(mensagem, errorResponse.mensagem)
        assertEquals(app, errorResponse.detalhe_erro.app)
        assertEquals(tipoErro, errorResponse.detalhe_erro.tipo_erro)
        assertEquals(mensagemInterna, errorResponse.detalhe_erro.mensagem_interna)
    }
}
