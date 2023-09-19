package com.example.auth.controllers;

import com.example.auth.domain.product.Product;
import com.example.auth.services.AbstractService;
import com.example.auth.services.impl.ProductImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("product")
public class ProductController extends RestControllerImpl<Product> {

    private final ProductImpl service;

    public ProductController(ProductImpl service) {
        this.service = service;
    }

    @Override
    protected AbstractService<Product> getService() {
        return this.service;
    }
}

