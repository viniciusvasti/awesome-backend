package com.vas.aos.infraestructure.persistence;

import com.vas.aos.infraestructure.persistence.models.UserJpaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPAQueryRepository extends JpaRepository<UserJpaModel, String> {
}