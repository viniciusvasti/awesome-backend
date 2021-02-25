package com.vas.aos.core.component.stock.application.dtos.event;

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

    @AllArgsConstructor
    @Data
    public static class Product {
        private int sku;
        private String name;
        private double price;
    }
}