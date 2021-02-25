package com.vas.aos.core.component.stock.application.repository;

import com.vas.aos.core.component.stock.domain.entities.Product;

import java.util.Arrays;
import java.util.List;

public interface ProductListRepository {
    List<Product> findAll();

    List<Product> findAllAvailable();
}