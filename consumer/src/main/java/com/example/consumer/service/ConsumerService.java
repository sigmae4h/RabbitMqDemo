package com.example.consumer.service;

import com.example.consumer.config.RabbitMqConfig;
import com.example.consumer.model.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {RabbitMqConfig.ALL_DATA_QUEUE, RabbitMqConfig.RED_DATA_QUEUE})
public class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitHandler
    public void receiveMessage(Data data) {
        logger.info("Message received: " + data.toString());
    }
}
