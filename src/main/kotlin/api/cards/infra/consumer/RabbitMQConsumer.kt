package api.cards.infra.consumer

import api.cards.api.exception.ErrorDetail
import api.cards.api.exception.ErrorResponse
import api.cards.domain.entity.vo.Cliente
import api.cards.domain.usecase.CardsOffersUseCase
import api.cards.infra.db.RedisService
import api.cards.infra.helper.ErrorHelper
import api.cards.infra.mapper.CardsOffersMapper
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class RabbitMQConsumer(
        private val cardsOffersUseCase: CardsOffersUseCase,
        private val errorProducer: ErrorProducer,
        private val redisService: RedisService): Consumer {

    @RabbitListener(queues = ["\${rabbitmq.config.queue}"])
    fun receiveMessage(message: String) {
        println("Mensagem recebida: $message")
        val clienteResponse = CardsOffersMapper.toClienteResponse(message)
        try {
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
        } catch (ex: Exception) {
            println("Erro ao processar mensagem: ${ex.message}")
            errorProducer.sendErrorMessage("Erro ao processar mensagem: ${ex.message}")
            val errorResponse = ErrorResponse(
                codigo = "422",
                mensagem = ex.message?: "Não conseguimos analisar as ofertas",
                detalhe_erro = ErrorDetail(
                    app = "api-client",
                    tipo_erro = "PROCESSAMENTO_FALHOU",
                    mensagem_interna = "Tivemos um problema na sua análise, revise seus dados."
                )
            )
            redisService.saveValue(clienteResponse.numeroSolicitacao, ErrorHelper.errorResponseToJson(errorResponse))
        }
    }
}