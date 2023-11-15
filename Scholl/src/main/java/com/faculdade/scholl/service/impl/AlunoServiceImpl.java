package com.faculdade.scholl.service.impl;

import com.faculdade.scholl.dto.AlunoDtoRequest;
import com.faculdade.scholl.dto.AlunoDtoResponse;
import com.faculdade.scholl.model.Aluno;
import com.faculdade.scholl.model.Curso;
import com.faculdade.scholl.repository.AlunoRepository;
import com.faculdade.scholl.repository.CursoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl extends RestImplAbstract<Aluno> {

    private final AlunoRepository repository;
    private final CursoRepository cursoRepository;

    public AlunoServiceImpl(AlunoRepository repository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    protected JpaRepository<Aluno, Long> getRepository() {
        return this.repository;
    }

}
