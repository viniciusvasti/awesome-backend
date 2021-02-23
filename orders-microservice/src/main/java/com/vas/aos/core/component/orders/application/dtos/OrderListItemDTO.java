package com.vas.aos.core.component.orders.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record OrderListItemDTO(@JsonProperty("id") UUID id,
                               @JsonProperty("customerName") String customerName,
                               @JsonProperty("products") List<ProductDTO> products,
                               @JsonProperty("payment") PaymentDTO payment,
                               @JsonProperty("approved") boolean approved,
                               @JsonProperty("paymentProcessed") boolean paymentProcessed) {
}