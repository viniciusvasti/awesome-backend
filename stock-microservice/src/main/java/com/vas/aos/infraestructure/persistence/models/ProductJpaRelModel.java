package com.vas.aos.infraestructure.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

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
    private int inventoryAmount;
}