package com.vas.aos.core.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vas.aos.core.domain.entities.ProcessStatus;

import java.util.List;
import java.util.UUID;

public record OrderListItemDTO(@JsonProperty("id") UUID id,
                               @JsonProperty("customerName") String customerName,
                               @JsonProperty("products") List<ProductDTO> products,
                               @JsonProperty("payment") PaymentDTO payment,
                               @JsonProperty("approved") ProcessStatus approved,
                               @JsonProperty("paymentProcessed") ProcessStatus paymentProcessed) {
}
