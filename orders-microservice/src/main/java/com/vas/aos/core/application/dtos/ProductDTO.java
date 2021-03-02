package com.vas.aos.core.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDTO(@JsonProperty("sku") int sku, @JsonProperty("name") String name,
                         @JsonProperty("price") double price) {
}
