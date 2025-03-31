package api.cards.infra.consumer

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ErrorProducer(
        private val rabbitTemplate: RabbitTemplate,

        @Value("\${rabbitmq.config.exchange}") private val exchange: String,
        @Value("\${rabbitmq.error.routing-key}") private val errorRoutingKey: String
) {
    fun sendErrorMessage(errorMessage: String) {
        println("Enviando mensagem de erro: $errorMessage")
        rabbitTemplate.convertAndSend(exchange, errorRoutingKey, errorMessage)
    }
}