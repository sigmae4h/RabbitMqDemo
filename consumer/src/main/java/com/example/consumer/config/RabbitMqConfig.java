package com.example.consumer.config;

import com.example.consumer.model.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
    public static final String DATA_EXCHANGE = "data_exchange";
    public static final String RED_DATA_QUEUE = "red_data_queue";
    public static final String BLUE_DATA_QUEUE = "blue_data_queue";
    public static final String ALL_DATA_QUEUE = "all_data_queue";

    private static final String RED_DATA_ROUTING_KEY = "red.data.*";
    private static final String BLUE_DATA_ROUTING_KEY = "blue.data.*";
    private static final String ALL_DATA_ROUTING_KEY = "*.data.#";

    @Bean
    Queue redQueue() {
        return new Queue(RED_DATA_QUEUE, false);
    }

    @Bean
    Queue blueQueue() {
        return new Queue(BLUE_DATA_QUEUE, false);
    }

    @Bean
    Queue allQueue() {
        return new Queue(ALL_DATA_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(DATA_EXCHANGE);
    }

    @Bean
    List<Binding> binding() {
        return Arrays.asList(
                BindingBuilder.bind(redQueue()).to(exchange()).with(RED_DATA_ROUTING_KEY),
                BindingBuilder.bind(blueQueue()).to(exchange()).with(BLUE_DATA_ROUTING_KEY),
                BindingBuilder.bind(allQueue()).to(exchange()).with(ALL_DATA_ROUTING_KEY));
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();

        jsonMessageConverter.setClassMapper(classMapper());

        return jsonMessageConverter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);

        template.setMessageConverter(messageConverter());

        return template;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();

        idClassMapping.put("com.example.producer.model.Data", Data.class);
        classMapper.setIdClassMapping(idClassMapping);

        return classMapper;
    }
}