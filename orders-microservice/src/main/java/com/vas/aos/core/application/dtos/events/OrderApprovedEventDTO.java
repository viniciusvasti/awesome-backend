package com.vas.aos.core.application.dtos.events;

import com.vas.aos.core.domain.entities.PaymentMethod;

import java.util.List;
import java.util.UUID;

public record OrderApprovedEventDTO(UUID id, String customerName,
                                    List<Product> products,
                                    Payment payment) {

    public static record Payment(PaymentMethod paymentMethod) {
    }

    public static record Product(int sku, String name, double price) {
    }
}
