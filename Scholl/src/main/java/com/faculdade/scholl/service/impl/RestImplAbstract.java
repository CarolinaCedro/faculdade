package com.faculdade.scholl.service.impl;

import com.faculdade.scholl.service.Rest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class RestImplAbstract<T> implements Rest<T> {

    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    public List<T> listAll() {
        return this.getRepository().findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return this.getRepository().findById(id);
    }

    @Override
    public T create(T entity) {
        return this.getRepository().save(entity);
    }

    @Override
    public T update(Long id, T entity) {
        return this.getRepository().save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.deleteById(id);
    }
}
