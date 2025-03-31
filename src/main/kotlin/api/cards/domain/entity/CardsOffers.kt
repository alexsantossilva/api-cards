package api.cards.domain.entity

import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.entity.vo.Cliente
import java.util.*
import kotlin.collections.List

data class CardsOffers(
        var numero_solicitacao: UUID,
        var data_solicitacao: String,
        val cliente: Cliente,
        var cartoes_ofertados: List<CartoesOfertados>
)