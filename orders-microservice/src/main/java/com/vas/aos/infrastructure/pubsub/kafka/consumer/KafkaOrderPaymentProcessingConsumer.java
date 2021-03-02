package com.vas.aos.infrastructure.pubsub.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vas.aos.core.application.dtos.events.OrderApprovedEventDTO;
import com.vas.aos.core.application.dtos.events.OrderPaymentProcessedEventDTO;
import com.vas.aos.core.application.service.GetOrderService;
import com.vas.aos.core.application.service.OrderPaymentProcessService;
import com.vas.aos.core.domain.entities.Order;
import com.vas.aos.infrastructure.pubsub.kafka.producer.KafkaOrderApprovedProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Component
public class KafkaOrderPaymentProcessingConsumer {

    private final ObjectMapper mapper;
    private final OrderPaymentProcessService orderPaymentProcessService;
    private final GetOrderService getOrderService;
    private final KafkaOrderApprovedProducer kafkaOrderApprovedProducer;

    @KafkaListener(topics = "${spring.kafka.topic.order-payment-processed}",
            groupId = "${spring.kafka.consumer-group-id}",
            clientIdPrefix = "order-payment-processed")
    public void listenToNewOrdersReceived(@Header(KafkaHeaders.CORRELATION_ID) String cid,
                                          @Header(KafkaHeaders.OFFSET) String offset,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partition,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                          @Payload JsonNode data) {
        MDC.put("CID", cid);
        log.info("Consuming topic: {}, partition: {}, offset: {}, CID: {}", topic, partition,
                offset, cid);
        log.info("Order payment processed: {}", data);
        try {
            OrderPaymentProcessedEventDTO orderPaymentProcessedEvent = mapper.treeToValue(data,
                    OrderPaymentProcessedEventDTO.class);
            log.info("orderPaymentProcessedEvent: {}", orderPaymentProcessedEvent);
            orderPaymentProcessService.markAsPaymentAsProcessed(orderPaymentProcessedEvent.id());
            Order order = getOrderService.getById(orderPaymentProcessedEvent.id());
            kafkaOrderApprovedProducer.publish(
                    new OrderApprovedEventDTO(
                            order.getId(),
                            order.getCustomerName(),
                            order.getProducts().stream().map(
                                    p -> new OrderApprovedEventDTO.Product(
                                            p.getSku(),
                                            p.getName(),
                                            p.getPrice()
                                    )
                            ).collect(Collectors.toList()),
                            new OrderApprovedEventDTO.Payment(order.getPayment().getPaymentMethod())));
        } catch (JsonProcessingException e) {
            // TODO emit alert here
            log.error("Error at parsing json to OrderPaymentProcessedEventDTO", e);
            e.printStackTrace();
        } finally {
            MDC.remove("CID");
        }
    }

}
