package com.vas.aos.core.application.dtos.events;

import com.vas.aos.core.domain.entities.PaymentMethod;

import java.util.UUID;

public record OrderProcessPaymentCommandDTO(UUID id,
                                            String customerName,
                                            Payment payment) {
    public static record Payment(PaymentMethod paymentMethod, double total) {
    }
}
