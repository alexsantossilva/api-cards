package api.cards.domain.strategy

import api.cards.domain.entity.vo.CartoesOfertados

interface CardsStrategy {

    fun getCardsOffers(): CartoesOfertados
}