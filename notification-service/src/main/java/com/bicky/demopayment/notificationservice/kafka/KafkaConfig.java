package com.bicky.demopayment.notificationservice.kafka;

import com.bickyraj.shared_dto.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, PaymentEvent> paymentEventConsumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> configProps = new HashMap<>(kafkaProperties.getConsumer().getProperties());
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Configure ErrorHandlingDeserializer
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        // Specify the target type for deserialization
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, PaymentEvent.class.getName());
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // Or specify specific packages

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(paymentEventConsumerFactory(kafkaProperties));
        factory.getContainerProperties().setPollTimeout(3000); // Set poll timeout if needed

        return factory;
    }

    @Bean
    public ProducerFactory<String, PaymentEvent> paymentEventProducerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> configProps = new HashMap<>(kafkaProperties.getProducer().getProperties());
        log.info(kafkaProperties.getBootstrapServers().toString());
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, PaymentEvent> paymentEventKafkaTemplate(KafkaProperties kafkaProperties) {
        return new KafkaTemplate<>(paymentEventProducerFactory(kafkaProperties));
    }
}
