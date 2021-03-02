package com.vas.aos.infrastructure.pubsub.kafka.producer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vas.aos.core.application.dtos.events.OrderProcessPaymentCommandDTO;
import com.vas.aos.core.application.pubsub.RequestPaymentPublisher;
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
public class KafkaRequestPaymentProducer implements RequestPaymentPublisher {

    @Value("${spring.kafka.topic.process-order-payment}")
    private String processOrderPaymentTopic;

    @Autowired
    private KafkaTemplate<String, JsonNode> kafkaTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void publish(final OrderProcessPaymentCommandDTO orderProcessPaymentCommand) {
        log.info("Publishing event {}", orderProcessPaymentCommand);
        JsonNode jsonMessage = mapper.valueToTree(orderProcessPaymentCommand);
        Message<JsonNode> message = MessageBuilder.withPayload(jsonMessage)
                .setHeader(KafkaHeaders.TOPIC, processOrderPaymentTopic)
                .setHeader(KafkaHeaders.TIMESTAMP, System.currentTimeMillis())
                .setHeader(KafkaHeaders.CORRELATION_ID, MDC.get("CID"))
                .build();

        ListenableFuture<SendResult<String, JsonNode>> future = kafkaTemplate.send(message);

        future.addCallback(
                result -> log.info("Success while publishing."),
                (KafkaFailureCallback<String, JsonNode>) ex -> {
                    ProducerRecord<String, JsonNode> failed = ex.getFailedProducerRecord();
                    log.error("Error while publishing", ex);
                });
    }
}
