package api.cards.api.exception

data class ErrorDetail(
    val app: String,
    val tipo_erro: String,
    val mensagem_interna: String
)
