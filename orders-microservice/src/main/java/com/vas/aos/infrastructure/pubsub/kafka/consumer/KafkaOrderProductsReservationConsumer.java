package com.vas.aos.infrastructure.pubsub.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vas.aos.core.application.dtos.events.OrderProductsReservedEventDTO;
import com.vas.aos.core.application.service.OrderProductsReservationService;
import com.vas.aos.core.application.service.RequestOrderPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class KafkaOrderProductsReservationConsumer {

    private final ObjectMapper mapper;
    private final OrderProductsReservationService orderProductsReservationService;
    private final RequestOrderPaymentService requestOrderPaymentService;

    @KafkaListener(topics = "${spring.kafka.topic.order-products-reserved}",
            groupId = "${spring.kafka.consumer-group-id}",
            clientIdPrefix = "order-products-reserved")
    public void listenToNewOrdersReceived(@Header(KafkaHeaders.CORRELATION_ID) String cid,
                                          @Header(KafkaHeaders.OFFSET) String offset,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partition,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                          @Payload JsonNode data) {
        MDC.put("CID", cid);
        log.info("Consuming topic: {}, partition: {}, offset: {}, CID: {}", topic, partition,
                offset, cid);
        log.info("Order products reserved: {}", data);
        try {
            OrderProductsReservedEventDTO productsReservedEvent = mapper.treeToValue(data,
                    OrderProductsReservedEventDTO.class);
            log.info("productsReservedEvent: {}", productsReservedEvent);
            requestOrderPaymentService.requestPayment(productsReservedEvent.id());
            orderProductsReservationService.markAsProductsReserved(productsReservedEvent.id());
        } catch (JsonProcessingException e) {
            // TODO emit alert here
            log.error("Error at parsing json to OrderProductsReservedEventDTO", e);
            e.printStackTrace();
        } finally {
            MDC.remove("CID");
        }
    }

}
