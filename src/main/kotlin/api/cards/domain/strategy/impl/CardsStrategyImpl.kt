package api.cards.domain.strategy.impl

import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.entity.vo.Cliente
import api.cards.domain.strategy.Cards.CashbackStrategy
import api.cards.domain.strategy.Cards.ParceirosStrategy
import api.cards.domain.strategy.Cards.SemAnuidadeStrategy
import java.time.LocalDate
import java.time.Period

object CardsStrategyImpl {
    fun getCardsOffers(cliente: Cliente, birthDate: LocalDate): List<CartoesOfertados> {
        val cardsList = mutableListOf<CartoesOfertados>()
        val age = getAge(birthDate)

        if (cliente.uf.uppercase() == "SP") {
            when {
                age < 25 -> {
                    when {
                        cliente.renda_mensal >= 5000.0 -> {
                            cardsList.add(CashbackStrategy().getCardsOffers())
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }

                        cliente.renda_mensal >= 1000.0 -> {
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }
                    }
                }

                age >= 25 && age < 30 -> {
                    when {
                        cliente.renda_mensal >= 5000.0 -> {
                            cardsList.add(CashbackStrategy().getCardsOffers())
                            cardsList.add(ParceirosStrategy().getCardsOffers())
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }

                        cliente.renda_mensal >= 3000.0 -> {
                            cardsList.add(ParceirosStrategy().getCardsOffers())
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }

                        cliente.renda_mensal >= 1000.0 -> cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                    }

                }

                else -> return cardsList
            }
        } else {
            when {
                age < 25 -> {
                    when {
                        cliente.renda_mensal >= 1000.0 -> {
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }
                    }
                }

                age >= 25 -> {
                    when {
                        cliente.renda_mensal >= 5000.0 -> {
                            cardsList.add(CashbackStrategy().getCardsOffers())
                            cardsList.add(ParceirosStrategy().getCardsOffers())
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }

                        cliente.renda_mensal >= 3000.0 -> {
                            cardsList.add(ParceirosStrategy().getCardsOffers())
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }

                        cliente.renda_mensal >= 1000.0 -> {
                            cardsList.add(SemAnuidadeStrategy().getCardsOffers())
                        }
                    }
                }

                else -> return cardsList
            }
        }

        return cardsList
    }

    fun getAge(birthDate: LocalDate): Int {
        val now = LocalDate.now()
        return Period.between(birthDate, now).years
    }
}