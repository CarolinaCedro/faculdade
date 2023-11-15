package com.faculdade.scholl.repository;

import com.faculdade.scholl.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
