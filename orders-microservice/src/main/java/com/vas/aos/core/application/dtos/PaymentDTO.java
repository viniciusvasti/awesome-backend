package com.vas.aos.core.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vas.aos.core.domain.entities.PaymentMethod;

public record PaymentDTO(@JsonProperty("paymentMethod") PaymentMethod paymentMethod) {
}
