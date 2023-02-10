package com.tonnie.messaging.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueueProducer {

    private final RabbitTemplate rabbitTemplate;
    
    private final TopicExchange topicExchange;
  
    private final Queue queue;
    
    @Value("${tonnie.message.topic.routingkey}")
	private String topicRoutingKey;

    public void send(String message) {
        rabbitTemplate.convertAndSend(this.queue.getName(), message);
    }
    
    public void notify(String message) {
    	rabbitTemplate.convertAndSend(topicExchange.getName(),topicRoutingKey, message);
    }
	
}
