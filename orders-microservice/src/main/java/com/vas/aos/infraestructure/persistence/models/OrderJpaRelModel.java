package com.vas.aos.infraestructure.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderJpaRelModel extends BaseModel {
    @Id
    private UUID id;
    private String customerName;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductJpaRelModel> products;
    @OneToOne
    @JoinColumn(name = "PAYMENT_ID")
    private PaymentJpaRelModel payment;
    private LocalDateTime paymentProcessedAt = null;
    private boolean paymentProcessed = false;
    private LocalDateTime stockInventoryCheckedAt = null;
    private boolean stockInventoryChecked = false;

    public OrderJpaRelModel(UUID id, String customerName, List<ProductJpaRelModel> products, PaymentJpaRelModel payment) {
        this.id = id;
        this.customerName = customerName;
        this.products = products;
        this.payment = payment;
    }
}