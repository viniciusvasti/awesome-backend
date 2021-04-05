package com.vas.aos.core.application.service.repository;

import com.vas.aos.core.domain.entities.User;

import java.util.Optional;

public interface FindUserRepository {
    Optional<User> findUser(String username);
}