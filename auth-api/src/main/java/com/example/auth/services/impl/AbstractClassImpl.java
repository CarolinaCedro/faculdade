package com.example.auth.services.impl;

import com.example.auth.services.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractClassImpl<T> implements AbstractService<T> {

    protected abstract JpaRepository<T, String> getRepository();

    @Override
    public void create(T entity) {
        getRepository().save(entity);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public Optional<T> getById(String id) {
        return getRepository().findById(id);
    }

    @Override
    public void update(String id, T entity) {
        Optional<T> optionalEntity = getRepository().findById(id);
        optionalEntity.ifPresent(existingEntity -> {
            // Atualizar os campos da entidade existente com base nos dados da nova entidade
            //fazer depois
            getRepository().save(existingEntity);
        });
    }

    @Override
    public void delete(String id) {
        getRepository().deleteById(id);
    }
}
