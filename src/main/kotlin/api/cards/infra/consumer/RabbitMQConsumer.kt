package api.cards.infra.consumer

import api.cards.domain.entity.vo.CartoesOfertados
import api.cards.domain.entity.vo.Cliente
import api.cards.domain.usecase.CardsOffersUseCase
import api.cards.domain.usecase.impl.CardsOffersUseCaseImpl
import api.cards.infra.dto.ClienteResponse
import api.cards.infra.mapper.CardsOffersMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class RabbitMQConsumer(private val cardsOffersUseCase: CardsOffersUseCase): Consumer {

    @RabbitListener(queues = ["\${rabbitmq.config.queue}"])
    fun receiveMessage(message: String) {
        println("Mensagem recebida: $message")
        val clienteResponse = CardsOffersMapper.toClienteResponse(message)
        val cliente = Cliente(
                clienteResponse.nome,
                clienteResponse.cpf,
                clienteResponse.idade,
                clienteResponse.dataNascimento,
                clienteResponse.uf,
                clienteResponse.rendaMensal,
                clienteResponse.email,
                clienteResponse.telefoneWhatsapp
        )
        cardsOffersUseCase.buildCardsOffers(
                cliente,
                18,
                clienteResponse.numeroSolicitacao,
                clienteResponse.dataSolicitacao
        )
    }
}