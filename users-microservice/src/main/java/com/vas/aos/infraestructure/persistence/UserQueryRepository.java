package com.vas.aos.infraestructure.persistence;

import com.vas.aos.core.application.service.repository.FindUserRepository;
import com.vas.aos.core.domain.entities.User;
import com.vas.aos.infraestructure.persistence.models.UserJpaModel;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
public class UserQueryRepository implements FindUserRepository {
    private final UserJPAQueryRepository repository;

    @Override
    public Optional<User> findUser(String username) {
        Optional<UserJpaModel> userModelOptional = repository.findById(username);
        try {
            var userModel = userModelOptional.get();
            return Optional.of(new User(userModel.getUserName(),
                    userModel.getPassword()));
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }
}