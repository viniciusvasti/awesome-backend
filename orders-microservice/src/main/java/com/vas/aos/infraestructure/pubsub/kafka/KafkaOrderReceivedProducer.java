package com.vas.aos.infraestructure.pubsub.kafka;

import com.vas.aos.core.component.orders.application.pubsub.OrderReceivedPublisher;
import com.vas.aos.core.component.orders.application.dtos.event.OrderReceivedEventDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaOrderReceivedProducer implements OrderReceivedPublisher {

    @Value("${spring.kafka.topic.new-order-received}")
    private String newOrderReceived;

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Override
    public void publish(OrderReceivedEventDTO message) {
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(
                MessageBuilder.withPayload(message.toString())
                        .setHeader(KafkaHeaders.TOPIC, newOrderReceived)
                        .setHeader(KafkaHeaders.TIMESTAMP, System.currentTimeMillis())
                        // TODO include Correlation ID
                        .build()
        );

        future.addCallback(result -> {
            // log here
        }, (KafkaFailureCallback<Integer, String>) ex -> {
            ProducerRecord<Integer, String> failed = ex.getFailedProducerRecord();
            // log here
        });
    }
}