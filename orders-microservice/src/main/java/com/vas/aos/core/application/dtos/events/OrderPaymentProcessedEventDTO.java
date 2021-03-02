package com.vas.aos.core.application.dtos.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record OrderPaymentProcessedEventDTO(@JsonProperty("id") UUID id) {
}
