package api.cards.infra.config.provider

import api.cards.infra.db.RedisService
import api.cards.infra.provider.processor.ProcessorProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CardsOffersProcessorProviderConfig {

    @Bean
    fun provider(redisService: RedisService): ProcessorProvider {
        return ProcessorProvider(redisService)
    }
}