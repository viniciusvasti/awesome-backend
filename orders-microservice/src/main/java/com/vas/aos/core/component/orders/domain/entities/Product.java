package com.vas.aos.core.component.orders.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Product {
    private final int sku;
    private final String name;
    private final double price;
}