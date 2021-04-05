package com.vas.aos.infraestructure.pubsub.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vas.aos.core.component.stock.application.dtos.event.OrderReceivedEventDTO;
import com.vas.aos.core.component.stock.application.exceptions.NotEnoughProductsException;
import com.vas.aos.core.component.stock.application.service.CheckProductStockService;
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
public class KafkaNewOrderReceivedConsumer {

    private final ObjectMapper mapper;
    private final CheckProductStockService reserveProductService;
    private final KafkaReserveProductsResponseProducer kafkaStockReserveResponseProducer;

    @KafkaListener(topics = "${spring.kafka.topic.new-order-received}",
            groupId = "${spring.kafka.consumer-group-id}",
            clientIdPrefix = "order-received")
    public void listenToNewOrdersReceived(@Header(KafkaHeaders.CORRELATION_ID) String cid,
                                          @Header(KafkaHeaders.OFFSET) String offset,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partition,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                          @Payload JsonNode data) {
        MDC.put("CID", cid);
        log.info("Consuming topic: {}, partition: {}, offset: {}, CID: {}", topic, partition,
                offset, cid);
        log.info("New order received: {}", data);
        try {
            OrderReceivedEventDTO orderReceivedEventDTO = mapper.treeToValue(data,
                    OrderReceivedEventDTO.class);
            reserveProductService.reserveProducts(orderReceivedEventDTO.getProducts());
            // TODO post kafka successful message for orders microservice
        } catch (JsonProcessingException e) {
            // TODO emit alert here
            log.error("Error at parsing json to OrderReceivedEventDTO", e);
            e.printStackTrace();
        } catch (NotEnoughProductsException ex) {
            log.warn("Order request denied: {}", ex.getMessage());
            // TODO post kafka failed message for orders microservice
        } finally {
            MDC.remove("CID");
        }
    }

}
