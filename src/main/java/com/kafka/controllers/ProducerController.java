package com.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.dto.Student;

@RestController
@RequestMapping("/produce")
public class ProducerController {
    @Value(value = "${spring.kafka.template.default-topic}")
    private String kafkaTopic;
    
    @Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@PostMapping("/message")
	public String sendMessage(@RequestBody Student message) {

		try {
			kafkaTemplate.send(kafkaTopic, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json message sent succuessfully";
	}
}
