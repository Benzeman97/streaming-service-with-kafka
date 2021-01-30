package com.benz.kafka.consumer.api.listener;

import com.benz.kafka.consumer.api.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"USER_TOPIC"},groupId = "user_GROup",containerFactory = "userKafkaListenerContainerFactory")
    public void consume(User user)
    {

        System.out.println(user);
    }
}
