package com.vas.aos.infrastructure.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductJpaRelModel extends BaseModel {
    @Id
    private UUID id;
    private int sku;
    private String name;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderJpaRelModel order;
}
