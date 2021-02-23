package com.vas.aos.core.component.orders.application.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vas.aos.core.component.orders.domain.entities.PaymentMethod;

public record PaymentDTO(@JsonProperty("paymentMethod") PaymentMethod paymentMethod) {
}