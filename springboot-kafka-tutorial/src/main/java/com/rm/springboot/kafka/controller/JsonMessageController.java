package com.rm.springboot.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rm.springboot.kafka.JsonKafkaProducer;
import com.rm.springboot.kafka.KafkaProducer;
import com.rm.springboot.kafka.model.User;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {
	private JsonKafkaProducer jsonKafkaProducer;

	public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
		this.jsonKafkaProducer = jsonKafkaProducer;
	}

	@PostMapping("/publishjson")
	public ResponseEntity<String> publish(@RequestBody User message) {

		jsonKafkaProducer.sendMessage(message);
		return ResponseEntity.ok("Json Message send successfully!!");

	}

}
