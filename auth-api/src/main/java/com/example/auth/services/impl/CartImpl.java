package com.example.auth.services.impl;

import com.example.auth.domain.cart.Cart;
import com.example.auth.repositories.CartRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CartImpl extends AbstractClassImpl<Cart> {

    private final CartRepository repository;

    public CartImpl(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Cart, String> getRepository() {
        return this.repository;
    }
}
