package api.cards.domain.entity.enums

enum class CardType(val value: Double) {
    CARTAO_SEM_ANUIDADE(1000.00),
    CARTAO_DE_PARCEIROS(3000.00),
    CARTAO_COM_CASHBACK(5000.00);

    fun getLimite(): Double {
        return value
    }
}