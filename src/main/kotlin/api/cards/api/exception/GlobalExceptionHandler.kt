package api.cards.api.exception

import api.cards.domain.exception.CardsOffersNotFoundException
import api.cards.domain.exception.CreditAnalysisException
import api.cards.infra.helper.ErrorHelper
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.format.DateTimeParseException

@RestControllerAdvice
class GlobalExceptionHandler() {

    private val appName = "api-cards"

    @ExceptionHandler(DateTimeParseException::class)
    fun handleDateTimeParseException(ex: DateTimeParseException, request: HttpServletRequest): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
                codigo = "400",
                mensagem = "Data de nascimento inválida: ${ex.message}",
                detalhe_erro = ErrorDetail(
                        app = appName,
                        tipo_erro = "VALIDACAO",
                        mensagem_interna = "A data de nascimento deve estar no formato YYYY-MM-DD"
                )
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException, request: HttpServletRequest): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
                codigo = "400",
                mensagem = ex.message ?: "Argumento inválido",
                detalhe_erro = ErrorDetail(
                        app = appName,
                        tipo_erro = "VALIDACAO",
                        mensagem_interna = "Os dados enviados não estão corretos"
                )
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception, request: HttpServletRequest): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
                codigo = "500",
                mensagem = "Um erro inesperado ocorreu.",
                detalhe_erro = ErrorDetail(
                        app = appName,
                        tipo_erro = "SERVICO_INDISPONIVEL",
                        mensagem_interna = "Tivemos um problema, mas fique tranquilo que nosso time já foi avisado."
                )
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }

    @ExceptionHandler(CardsOffersNotFoundException::class)
    fun CardsOffersNotFoundException(ex: CardsOffersNotFoundException, request: HttpServletRequest): ResponseEntity<Any> {
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(CreditAnalysisException::class)
    fun CreditAnalysisException(ex: CreditAnalysisException, request: HttpServletRequest): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
                codigo = "422",
                mensagem = ex.message ?: "Dados insuficientes para análise",
                detalhe_erro = ErrorDetail(
                        app = appName,
                        tipo_erro = "PROCESSAMENTO_FALHOU",
                        mensagem_interna = "Tivemos um problema na sua análise, revise seus dados."
                )
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse)
    }

    @ExceptionHandler(ErrorResponseException::class)
    fun ErrorResponseException(ex: ErrorResponseException, request: HttpServletRequest): ResponseEntity<Any> {
        val errorResponse = ErrorHelper.toErrorResponse(ex.message.toString())
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse)
    }
}