package com.vas.aos.core.component.stock.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDTO(@JsonProperty("sku") int sku,
                         @JsonProperty("name") String name,
                         @JsonProperty("price") double price,
                         @JsonProperty("inventoryAmount") int inventoryAmount) {
}