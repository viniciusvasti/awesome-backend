package com.vas.aos.core.component.orders.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PaymentMethod {
    CREDIT, DEBIT
}