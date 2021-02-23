package com.vas.aos.infraestructure.persistence;

import com.vas.aos.infraestructure.persistence.models.OrderJpaRelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OrderMySQLRepository extends JpaRepository<OrderJpaRelModel, UUID> {
}