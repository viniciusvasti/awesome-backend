package com.vas.aos.core.application.dtos.events;

import com.vas.aos.core.domain.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class OrderReceivedEventDTO {
    private UUID id;
    private String customerName;
    private List<Product> products;
    private Payment payment;

    @AllArgsConstructor
    @Data
    public static class Payment {
        private PaymentMethod paymentMethod;
    }

    @AllArgsConstructor
    @Data
    public static class Product {
        private int sku;
        private String name;
        private double price;
    }
}
