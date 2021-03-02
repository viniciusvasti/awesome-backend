package com.vas.aos.infrastructure.persistence.models;

import com.vas.aos.core.domain.entities.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
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
    private ProcessStatus paymentProcessed = ProcessStatus.PENDING;
    private LocalDateTime stockInventoryCheckedAt = null;
    private ProcessStatus stockInventoryChecked = ProcessStatus.PENDING;

    public OrderJpaRelModel(UUID id, String customerName, List<ProductJpaRelModel> products, PaymentJpaRelModel payment) {
        this.id = id;
        this.customerName = customerName;
        this.products = products;
        this.payment = payment;
    }
}
