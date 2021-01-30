package com.benz.kafka.producer.api.controller;

import com.benz.kafka.producer.api.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private KafkaTemplate<String,User> kafkaTemplate;
    final private static String TOPIC="USER_TOPIC";

    public UserController(KafkaTemplate<String,User> kafkaTemplate)
    {
        this.kafkaTemplate=kafkaTemplate;
    }

    @PostMapping("/send")
    public void sendUser(@RequestBody User user)
    {
        kafkaTemplate.send(TOPIC,user);
        System.out.println("Message has been sent");
    }
}
