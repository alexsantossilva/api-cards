package api.cards.domain.strategy.Cards

import api.cards.domain.entity.enums.CardType
import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.strategy.CardsStrategy

class ParceirosStrategy: CardsStrategy {
    override fun getCardsOffers(): CartoesOfertados {
        return CartoesOfertados(
                CardType.CARTAO_DE_PARCEIROS.name,
                20.00,
                CardType.CARTAO_DE_PARCEIROS.getLimite(),
                "APROVADO"
        )
    }
}