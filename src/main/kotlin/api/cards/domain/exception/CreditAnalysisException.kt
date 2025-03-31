package api.cards.domain.exception

class CreditAnalysisException(message: String, val protocol: String): RuntimeException(message)