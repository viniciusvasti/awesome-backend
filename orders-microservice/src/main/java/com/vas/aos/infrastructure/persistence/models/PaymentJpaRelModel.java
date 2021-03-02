package com.vas.aos.infrastructure.persistence.models;

import com.vas.aos.core.domain.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentJpaRelModel extends BaseModel {
    @Id
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
