package com.vas.aos.core.application.dtos.events;

import java.util.UUID;

public record OrderPaymentFailedEventDTO(UUID id, String message) {
}
