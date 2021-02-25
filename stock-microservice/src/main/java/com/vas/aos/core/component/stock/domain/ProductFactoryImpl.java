package com.vas.aos.core.component.stock.domain;

import com.vas.aos.core.component.stock.domain.entities.Product;

public class ProductFactoryImpl implements ProductFactory {
    @Override
    public Product create(int sku, String name, double price, int inventoryAmount) {
        return new Product(sku, name, price, inventoryAmount);
    }
}