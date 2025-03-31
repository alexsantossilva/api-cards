package api.cards.domain.strategy.Cards

import api.cards.domain.entity.enums.CardType
import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.strategy.CardsStrategy

class CashbackStrategy: CardsStrategy {
    override fun getCardsOffers(): CartoesOfertados {
        return CartoesOfertados(
                CardType.CARTAO_COM_CASHBACK.name,
        40.00,
                CardType.CARTAO_COM_CASHBACK.getLimite(),
                "APROVADO"
        )

    }
}