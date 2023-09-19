package com.example.auth.services.impl;

import com.example.auth.domain.user.User;
import com.example.auth.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserImpl extends AbstractClassImpl<User> {

    private final UserRepository repository;

    public UserImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return this.repository;
    }
}
