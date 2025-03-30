package api.cards.domain.entity.vo

data class CartoesOfertados(
        val tipo_cartao: String,
        val valor_anuidade_mensal: Double,
        val valor_limite_disponivel: Double,
        val status: String
)
