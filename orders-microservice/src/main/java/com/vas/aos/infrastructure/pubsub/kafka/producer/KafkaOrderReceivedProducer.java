package com.vas.aos.infrastructure.pubsub.kafka.producer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vas.aos.core.application.dtos.events.OrderReceivedEventDTO;
import com.vas.aos.core.application.pubsub.OrderReceivedPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
public class KafkaOrderReceivedProducer implements OrderReceivedPublisher {

    @Value("${spring.kafka.topic.new-order-received}")
    private String newOrderReceivedTopic;

    @Autowired
    private KafkaTemplate<String, JsonNode> kafkaTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void publish(OrderReceivedEventDTO message) {
        log.info("Publishing event {}", message);
        JsonNode jsonMessage = mapper.valueToTree(message);
        Message<JsonNode> m = MessageBuilder.withPayload(jsonMessage)
                .setHeader(KafkaHeaders.TOPIC, newOrderReceivedTopic)
                .setHeader(KafkaHeaders.TIMESTAMP, System.currentTimeMillis())
                .setHeader(KafkaHeaders.CORRELATION_ID, MDC.get("CID"))
                .build();

        ListenableFuture<SendResult<String, JsonNode>> future =
                kafkaTemplate.send(m);

        future.addCallback(
                result -> log.info("Success while publishing"),
                (KafkaFailureCallback<String, JsonNode>) ex -> {
                    ProducerRecord<String, JsonNode> failed = ex.getFailedProducerRecord();
                    log.error("Error while publishing", ex);
                });
    }
}
