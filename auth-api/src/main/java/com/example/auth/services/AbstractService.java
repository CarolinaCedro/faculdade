package com.example.auth.services;

import com.example.auth.domain.product.ProductRequestDTO;
import com.example.auth.domain.product.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T> {

    void create(T body);

    List<T> getAll();

    Optional<T> getById(String id);

    void update(String id, T body);

    void delete(String id);
}

