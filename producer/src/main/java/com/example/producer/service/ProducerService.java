package com.example.producer.service;

import com.example.producer.config.RabbitMqConfig;
import com.example.producer.model.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private Map<String, String> routeKeys = new HashMap<String, String>() {
        {
            put("red.data.orange", "Orange");
            put("red.data.red", "Red");
            put("red.data.black", "Black");
            put("blue.data.black", "Black");
            put("blue.data.yellow", "Yellow");
        }
    };

    @RequestMapping("/run")
    public ResponseEntity produce() {
        routeKeys.forEach((key, value) -> {
            logger.info("Sending {} to {}", value, key);

            Data data = new Data(key, value);

            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, key, data);
        });

        return ResponseEntity.ok("Requests sent to RabbitMQ");
    }
}
