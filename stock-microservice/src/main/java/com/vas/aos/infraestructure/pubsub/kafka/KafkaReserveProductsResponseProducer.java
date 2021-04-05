package com.vas.aos.infraestructure.pubsub.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.vas.aos.core.component.stock.application.dtos.event.OrderReceivedEventDTO;
import com.vas.aos.core.component.stock.application.pubsub.ReserveProductsResponsePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaReserveProductsResponseProducer implements ReserveProductsResponsePublisher {

    @Value("${spring.kafka.topic.new-order-received}")
    private String newOrderReceived;

    @Autowired
    private KafkaTemplate<String, JsonNode> kafkaTemplate;

    @Override
    public void publish(OrderReceivedEventDTO message) {
        log.info("Publishing event {}", message);
//        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(
//                MessageBuilder.withPayload(message.toString())
//                        .setHeader(KafkaHeaders.TOPIC, newOrderReceived)
//                        .setHeader(KafkaHeaders.TIMESTAMP, System.currentTimeMillis())
//                        .setHeader(KafkaHeaders.CORRELATION_ID, MDC.get("CID"))
//                        .build()
//        );

//        future.addCallback(result -> {
//            log.info("Success while publishing");
//        }, (KafkaFailureCallback<Integer, String>) ex -> {
//            ProducerRecord<Integer, String> failed = ex.getFailedProducerRecord();
//            log.error("Error while publishing", ex);
//        });
    }
}
