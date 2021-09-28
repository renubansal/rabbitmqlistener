package com.rabbitmq.rabbitmqlistener;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue myQueue() {
        return new Queue("MyQueue", false);
    }

    @Bean
    Queue myQueueUsingBuilder() {
        return QueueBuilder.durable("AnotherQueue").autoDelete().build();
    }

    @Bean
    Binding myBinding() {
        //one way
//        return new Binding("MyQueue", Binding.DestinationType.QUEUE, "TopicExchange", "topic", null);
        return BindingBuilder
                .bind(myQueue())
                .to(new TopicExchange("ATopicExchange"))
                .with("topic");
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory());
        messageListenerContainer.setQueues(myQueue());
        messageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return messageListenerContainer;
    }


}
