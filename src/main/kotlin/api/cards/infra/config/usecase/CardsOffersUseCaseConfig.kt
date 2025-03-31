package api.cards.infra.config.usecase

import api.cards.domain.ports.processor.CardsOffersProcessorProvider
import api.cards.domain.usecase.CardsOffersUseCase
import api.cards.domain.usecase.impl.CardsOffersUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CardsOffersUseCaseConfig {

    @Bean
    fun getCardsOffersUseCase(cardsOffersProcessorProvider: CardsOffersProcessorProvider): CardsOffersUseCase {
        return CardsOffersUseCaseImpl(cardsOffersProcessorProvider)
    }
}