package com.rabbitmq.rabbitmqlistener;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfig {

    @Bean
    public Exchange myExchange() {
        return new TopicExchange("TopicExchange");
    }

    @Bean
    Exchange myAnotherExchange() {
        return ExchangeBuilder
                .directExchange("DirectExchange")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean
    Exchange myNewTopicExchange() {
        return ExchangeBuilder
                .topicExchange("NewTopicExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }
}
