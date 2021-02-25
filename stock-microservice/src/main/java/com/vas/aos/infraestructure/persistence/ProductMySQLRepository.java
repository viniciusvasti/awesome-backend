package com.vas.aos.infraestructure.persistence;

import com.vas.aos.core.component.stock.domain.entities.Product;
import com.vas.aos.infraestructure.persistence.models.ProductJpaRelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


// TODO create table for reserving products asked for Order
@Repository
interface ProductMySQLRepository extends JpaRepository<ProductJpaRelModel, UUID> {
    List<Product> findByInventoryAmountGreaterThan(int inventoryAmount);
}