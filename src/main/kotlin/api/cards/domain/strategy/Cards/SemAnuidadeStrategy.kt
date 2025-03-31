package api.cards.domain.strategy.Cards

import api.cards.domain.entity.enums.CardType
import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.strategy.CardsStrategy

class SemAnuidadeStrategy: CardsStrategy {
    override fun getCardsOffers(): CartoesOfertados {
        return CartoesOfertados(
                CardType.CARTAO_SEM_ANUIDADE.name,
                00.00,
                CardType.CARTAO_SEM_ANUIDADE.getLimite(),
                "APROVADO"
        )
    }
}