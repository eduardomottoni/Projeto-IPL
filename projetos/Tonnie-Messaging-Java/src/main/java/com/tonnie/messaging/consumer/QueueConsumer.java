package com.tonnie.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class QueueConsumer {
	
	@RabbitListener(queues = {"${tonnie.message.queue.name}"})
	public void consume(@Payload String payload) {
		log.info("New queue message Received: {}", payload);
		
	}
	
	@RabbitListener(queues = {"${tonnie.message.topic.name}"})
	public void consumeTopic(@Payload String payload) {
		log.info("New topic message Received: {}", payload);
	}
}
