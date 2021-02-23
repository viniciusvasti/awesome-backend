package com.vas.aos.core.component.orders.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Payment {
    private final PaymentMethod paymentMethod;
}