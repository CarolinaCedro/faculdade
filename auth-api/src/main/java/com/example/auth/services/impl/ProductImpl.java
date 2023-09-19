package com.example.auth.services.impl;

import com.example.auth.domain.product.Product;
import com.example.auth.repositories.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductImpl extends AbstractClassImpl<Product> {

    private final ProductRepository repository;

    public ProductImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Product, String> getRepository() {
        return this.repository;
    }
}
