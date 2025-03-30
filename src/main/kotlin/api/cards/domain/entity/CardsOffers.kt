package api.client.domain.entity

import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.entity.vo.Cliente
import java.util.*

data class CardsOffers(
        var numero_solicitacao: UUID = UUID.randomUUID(),
        val data_solicitacao: String,
        val cliente: Cliente,
        val cartoes_ofertados: CartoesOfertados
)