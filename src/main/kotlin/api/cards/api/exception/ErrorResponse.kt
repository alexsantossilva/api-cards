package api.cards.api.exception

data class ErrorResponse(
    val codigo: String,
    val mensagem: String,
    val detalhe_erro: ErrorDetail
)