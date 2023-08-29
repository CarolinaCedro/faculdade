package io.github.carolinacedro.application;

import java.util.Optional;

public interface TodoList {

    void getAllTasks();

    Optional<String> findTaskById();

    void delete();


    String create();
}
