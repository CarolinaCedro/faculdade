package com.example.auth.controllers;

import com.example.auth.domain.cart.Cart;
import com.example.auth.services.AbstractService;
import com.example.auth.services.impl.CartImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("cart")
public class CartController extends RestControllerImpl<Cart> {

    private final CartImpl service;

    public CartController(CartImpl service) {
        this.service = service;
    }

    @Override
    protected AbstractService<Cart> getService() {
        return this.service;
    }
}

