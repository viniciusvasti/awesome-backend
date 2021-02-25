package com.vas.aos.infraestructure.persistence;

import com.vas.aos.core.component.stock.application.repository.ProductListRepository;
import com.vas.aos.core.component.stock.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class ProductQueryRepository implements ProductListRepository {

    private final ProductMySQLRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(
                p -> new Product(p.getSku(),
                        p.getName(),
                        p.getPrice(),
                        p.getInventoryAmount()
                )).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllAvailable() {
        return repository.findByInventoryAmountGreaterThan(0).stream().map(
                p -> new Product(p.getSku(),
                        p.getName(),
                        p.getPrice(),
                        p.getInventoryAmount()
                )).collect(Collectors.toList());
    }
}