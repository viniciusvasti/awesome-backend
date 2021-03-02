package com.vas.aos.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Product {
    int sku;
    String name;
    double price;
}
