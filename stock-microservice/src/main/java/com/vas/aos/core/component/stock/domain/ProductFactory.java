package com.vas.aos.core.component.stock.domain;

import com.vas.aos.core.component.stock.domain.entities.Product;

public interface ProductFactory {
    Product create(int sku, String name, double price, int inventoryAmount);
}