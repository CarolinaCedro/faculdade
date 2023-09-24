package org.example.application.controller.service;

import java.util.ArrayList;

public interface ContatosService<T> {
    long create(T contato);

    void update(Long id, T contato);

    void delete(Long id);

    ArrayList<T> findAllPessoa();

    T findContato(Long id);

    int findId(T contato);

    Boolean isExist(Long id);
}

