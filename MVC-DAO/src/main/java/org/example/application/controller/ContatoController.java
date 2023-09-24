package org.example.application.controller;

import java.util.List;
import java.util.Optional;

public interface ContatoController<T> {

    List<T> getAllContatos();

    Optional<T> getOneContato(Long id);

    long createContato(T body);

    void updateContato(Long id, T body);

    long deleteContato(Long id);


}
