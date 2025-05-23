package com.fitness.aiservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.name}")
    private String Queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    @Bean
//    this is the queue that will be used to send and receive messages
//    name of the queue is activity.queue
//    true here means the queue is durable and that it will not be lost if the server crashes or restarts
    public Queue activityQueue() {
        return new Queue(Queue, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(Queue activityQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(activityQueue).to(directExchange).with(routingKey);
    }

    //    Java Object to JSON converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
