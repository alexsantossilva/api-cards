package api.cards.infra.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ClienteResponse(
        @JsonProperty("id") val numeroSolicitacao: String,
        @JsonProperty("data_solicitacao") val dataSolicitacao: String,
        @JsonProperty("nome") val nome: String,
        @JsonProperty("cpf") val cpf: String,
        @JsonProperty("idade") val idade: Int,
        @JsonProperty("data_nascimento") val dataNascimento: String,
        @JsonProperty("uf") val uf: String,
        @JsonProperty("renda_mensal") val rendaMensal: Double,
        @JsonProperty("email") val email: String,
        @JsonProperty("telefone_whatsapp") val telefoneWhatsapp: String
)
