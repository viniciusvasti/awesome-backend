package com.vas.aos.core.component.orders.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDTO(@JsonProperty("sku") int sku, @JsonProperty("name") String name,
                         @JsonProperty("price") double price) {
}