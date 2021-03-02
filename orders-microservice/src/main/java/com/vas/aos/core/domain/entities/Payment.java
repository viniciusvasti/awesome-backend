package com.vas.aos.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Payment {
    PaymentMethod paymentMethod;
}
