package com.santander.kafka.producer;

import com.google.gson.Gson;
import com.santander.kafka.model.DemoModel;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.host}")
    private String host;

    @Value("${kafka.port}")
    private int port;

    @Value("${kafka.key.serializer}")
    private String keySerializer;

    @Value("${kafka.value.serializer}")
    private String valueSerializer;

    @Bean
    public ProducerFactory<String, String> producerFactory() throws ClassNotFoundException {
        Map<String, Object> config = new HashMap<>();
        String defaultBootstrapServer = (bootstrapServers != null && !"".equals(bootstrapServers)) ? bootstrapServers : host + ":" + port;
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, defaultBootstrapServer);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer != null ? Class.forName(keySerializer) : org.apache.kafka.common.serialization.StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer != null ? Class.forName(valueSerializer) : org.springframework.kafka.support.serializer.JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() throws ClassNotFoundException {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Gson jsonConverter() {
        return new Gson();
    }

}
