package com.vas.aos.core.component.orders.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreatedOrderDTO(@JsonProperty("id") UUID id) {
}