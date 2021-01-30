package com.benz.kafka.consumer.api.config;

import com.benz.kafka.consumer.api.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Value("${kafka.producer.host_1}")
    private String host_1;
    @Value("${kafka.producer.port_1}")
    private String port_1;
/*

    @Bean
    public ConsumerFactory<String, User> userConsumerFactory()
    {

        Map<String,Object> configs=new ConcurrentHashMap<>();

        //config producer location
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9093");

        configs.put(ConsumerConfig.GROUP_ID_CONFIG,"USER_GROUP");

        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),new JsonDeserializer<>(User.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,User> userKafkaListenerContainerFactory(ConsumerFactory<String,User> userConsumerFactory)
    {
        ConcurrentKafkaListenerContainerFactory<String,User> kafkaListenerContainerFactory
                =new ConcurrentKafkaListenerContainerFactory<>();

        kafkaListenerContainerFactory.setConsumerFactory(userConsumerFactory);

        return kafkaListenerContainerFactory;
    }


*/

    @Bean
    public ConsumerFactory<String,User> userConsumerFactory() {

        JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> config = new ConcurrentHashMap<>();

        String producerLoc = host_1.concat(":").concat(port_1);

        //config producer
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, producerLoc);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "user_GROup");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,deserializer);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory(ConsumerFactory<String,User> userConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, User> factory
                = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(userConsumerFactory);

        return factory;

    }


}
