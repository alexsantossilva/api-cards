package api.cards.domain.entity.enums

enum class CardType(val value: Double) {
    CARTAO_SEM_ANUIDADE(1000.00),
    CARTAO_COM_CASHBACK(2000.00),
    CARTAO_DE_PARCEIROS(1500.00);

    fun getValor(): Double {
        return value
    }
}