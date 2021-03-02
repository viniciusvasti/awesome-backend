package com.vas.aos.core.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreatedOrderDTO(@JsonProperty("id") UUID id) {
}
