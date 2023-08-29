package io.carolinacedro.apirest.application.controller;

import io.carolinacedro.apirest.application.service.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public abstract class AbstractController<T> {
    protected abstract AbstractService<T> getService();

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        List<T> data = getService().getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<T>> findById(@PathVariable Long id) {
        Optional<T> data = getService().getById(id);
        return ResponseEntity.ok(data);
    }


    @PostMapping
    public ResponseEntity<T> create(@RequestBody T body) {
        return ResponseEntity.ok(getService().create(body));
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        getService().deleteById(id);
    }
}
