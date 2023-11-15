package com.faculdade.scholl.controller;

import com.faculdade.scholl.service.impl.RestImplAbstract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public abstract class AbstractController<T> {

    protected abstract RestImplAbstract<T> getService();

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        List<T> data = getService().listAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public Optional<T> findById(@PathVariable Long id) {
        return getService().findById(id);
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T body) {
        return ResponseEntity.ok(getService().create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, T body) {
        return ResponseEntity.ok(getService().update(id, body));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        getService().deleteById(id);
    }


}
