package com.tonnie.messaging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonnie.messaging.producer.QueueProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tonnie")
@RequiredArgsConstructor
public class TestController {

	private final QueueProducer producer;
	
	private Integer counter = 1;
	
	@GetMapping(value = "/send/{message}")
	public String send(@PathVariable("message") String message) {
		
		producer.send(String.format("%s_%s",message,counter++));
		
		return "OK";
	}
	
	@GetMapping(value = "/notify/{message}")
	public String notify(@PathVariable("message") String message) {
		
		producer.notify(String.format("%s_%s_TOPIC",message,counter++));
		
		return "OK";
	}
	
}
