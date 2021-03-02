package com.vas.aos.core.application.dtos.events;

import java.util.UUID;

public record OrderProductsReservationFailedEventDTO(UUID id,
                                                     String message) {
}
