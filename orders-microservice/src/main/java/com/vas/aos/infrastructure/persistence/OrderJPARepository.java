package com.vas.aos.infrastructure.persistence;

import com.vas.aos.infrastructure.persistence.models.OrderJpaRelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OrderJPARepository extends JpaRepository<OrderJpaRelModel, UUID> {
}
