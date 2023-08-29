package io.carolinacedro.apirest.application.service;

import io.carolinacedro.apirest.application.domain.Request;
import io.carolinacedro.apirest.application.domain.Response;
import io.carolinacedro.apirest.application.domain.dto.ResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractServiceImpl<T> implements AbstractService<T> {

    protected abstract JpaRepository<T, Long> getRepository();


    @Override
    public T create(ResponseDto body) {
        return this.getRepository().save(body);
    }

    @Override
    public List<T> getAll() {
        return this.getRepository().findAll();
    }

    @Override
    public Optional<T> getById(Long id) {
        return this.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.getRepository().deleteById(id);
    }

}
