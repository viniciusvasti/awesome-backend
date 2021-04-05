package com.vas.aos.core.application.service;

import com.vas.aos.core.application.service.repository.FindUserRepository;
import com.vas.aos.core.domain.entities.User;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FindUserServiceImpl implements FindUserService {
    private final FindUserRepository userQueryRepository;

    @Override
    public Optional<User> findUser(String username) {
        return userQueryRepository.findUser(username);
    }
}