package com.vas.aos.core.application.service;

import com.vas.aos.core.domain.entities.User;

import java.util.Optional;

public interface FindUserService {
    Optional<User> findUser(String username);
}