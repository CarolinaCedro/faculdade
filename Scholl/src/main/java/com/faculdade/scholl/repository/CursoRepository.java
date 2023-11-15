package com.faculdade.scholl.repository;

import com.faculdade.scholl.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
