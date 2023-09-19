package com.example.auth.controllers;

import com.example.auth.services.AbstractService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public abstract class RestControllerImpl<T> implements RestController<T> {

    protected abstract AbstractService<T> getService();

    @Override
    public ResponseEntity<Void> create(T entity) {
        getService().create(entity);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = getService().getAll();
        return ResponseEntity.ok(entities);
    }

    @Override
    public ResponseEntity<Optional<T>> getById(String id) {
        return ResponseEntity.ok(this.getService().getById(id));
    }

    @Override
    public ResponseEntity<Void> update(String id, T entity) {
        getService().update(id, entity);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        getService().delete(id);
        return ResponseEntity.ok().build();
    }
}
