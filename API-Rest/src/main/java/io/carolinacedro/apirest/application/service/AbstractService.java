package io.carolinacedro.apirest.application.service;

import io.carolinacedro.apirest.application.domain.dto.ResponseDto;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T> {


    List<T> getAll();

    Optional<T> getById(Long id);

    void deleteById(Long id);

}
