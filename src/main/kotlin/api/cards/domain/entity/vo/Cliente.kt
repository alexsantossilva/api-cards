package api.cards.domain.entity.vo

data class Cliente(
        val nome: String,
        val cpf: String,
        val idade: Int,
        val data_nascimento: String,
        val uf: String,
        val renda_mensal: Double,
        val email: String,
        val telefone_whatsapp: String
)