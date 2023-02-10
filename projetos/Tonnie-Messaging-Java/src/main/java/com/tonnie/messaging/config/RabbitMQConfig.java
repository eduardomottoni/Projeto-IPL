package com.tonnie.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${tonnie.message.queue.name}")
	private String queueName;

	@Value("${tonnie.message.topic.name}")
	private String topicName;
	
	@Value("${tonnie.message.topic.exchanger}")
	private String topicExchanger;
	
	@Value("${tonnie.message.topic.routingkey}")
	private String topicRoutingKey;

	@Bean
	public Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	public Queue topic() {
		return new Queue(topicName, false);
	}
	
	@Bean
	public TopicExchange exchange() {
        return new TopicExchange(topicExchanger);
    }
	
	@Bean
    Binding bindingTopic(Queue topic, TopicExchange exchange) {
        return BindingBuilder.bind(topic).to(exchange).with(topicRoutingKey);
    }
	
}
