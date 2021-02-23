package com.vas.aos.infraestructure.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderJpaRelModel {
    @Id
    private UUID id;
    private String customerName;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductJpaRelModel> products;
    @OneToOne
    @JoinColumn(name = "PAYMENT_ID")
    private PaymentJpaRelModel payment;
}