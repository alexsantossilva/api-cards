package api.cards.infra.config.consumer

import com.rabbitmq.client.ConnectionFactory
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory

@EnableRabbit
@Configuration
class RabbitMQConfig(
    @Value("\${rabbitmq.host}") private val host: String,
    @Value("\${rabbitmq.port}") private val port: Int,
    @Value("\${rabbitmq.username}") private val username: String,
    @Value("\${rabbitmq.password}") private val password: String,

    @Value("\${rabbitmq.config.queue}") private val queue: String,
    @Value("\${rabbitmq.config.exchange}") private val exchange: String,
    @Value("\${rabbitmq.config.routing-key}") private val routingKey: String,

    @Value("\${rabbitmq.error.queue}") private val errorQueue: String,
    @Value("\${rabbitmq.error.routing-key}") private val errorRoutingKey: String
) {

    @Bean
    fun connectionFactory(): CachingConnectionFactory {
        val factory = CachingConnectionFactory(host)
        factory.port = port
        factory.username = username
        factory.setPassword(password)
        return factory
    }

    @Bean
    fun queue(): Queue {
        return Queue(queue, true)
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(exchange)
    }

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey)
    }
    @Bean
    fun errorQueue(): Queue = Queue(errorQueue, true)

    @Bean
    fun errorBinding(): Binding {
        return BindingBuilder.bind(errorQueue()).to(exchange()).with(errorRoutingKey)
    }

}
