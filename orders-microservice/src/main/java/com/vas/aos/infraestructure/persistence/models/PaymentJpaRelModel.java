package com.vas.aos.infraestructure.persistence.models;

import com.vas.aos.core.component.orders.domain.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentJpaRelModel extends BaseModel{
    @Id
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}