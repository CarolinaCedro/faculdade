package com.faculdade.scholl.service;

import java.util.List;
import java.util.Optional;

public interface Rest<T> {

    List<T> listAll();

    Optional<T> findById(Long id);

    T create(T entity);

    T update(Long id, T entity);

    void deleteById(Long id);


}
