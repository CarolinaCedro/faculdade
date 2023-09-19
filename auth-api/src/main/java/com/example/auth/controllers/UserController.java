package com.example.auth.controllers;

import com.example.auth.domain.user.User;
import com.example.auth.services.AbstractService;
import com.example.auth.services.impl.UserImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController extends RestControllerImpl<User> {

    private final UserImpl service;

    public UserController(UserImpl service) {
        this.service = service;
    }

    @Override
    protected AbstractService<User> getService() {
        return this.service;
    }
}

