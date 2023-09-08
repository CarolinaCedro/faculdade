package io.carolinacedro.apirest.application.service;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T> {


    List<T> getAll();

    Optional<T> getById(Long id);

    T create(T body);

    void deleteById(Long id);

}
