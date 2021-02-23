package com.vas.aos.infraestructure.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductJpaRelModel {
    @Id
    private int sku;
    private String name;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderJpaRelModel order;
}