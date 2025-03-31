package api.cards.infra.consumer

import api.cards.domain.entity.vo.Cliente
import api.cards.domain.usecase.CardsOffersUseCase
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class RabbitMQConsumer(cardsOffersUseCase: CardsOffersUseCase): Consumer {

    @RabbitListener(queues = ["\${rabbitmq.config.queue}"])
    fun receiveMessage(message: String) {
        println("Mensagem recebida: $message")
    }

    fun parseData(json: String): Cliente {
        val objectMapper = jacksonObjectMapper()
        return objectMapper.readValue(json, Cliente::class.java)
    }
}