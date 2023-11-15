package com.faculdade.scholl.service.impl;

import com.faculdade.scholl.dto.CursoDtoRequest;
import com.faculdade.scholl.model.Aluno;
import com.faculdade.scholl.model.Curso;
import com.faculdade.scholl.repository.AlunoRepository;
import com.faculdade.scholl.repository.CursoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl extends RestImplAbstract<Curso> {

    private final CursoRepository repository;
    private final AlunoRepository alunoRepository;

    public CursoServiceImpl(CursoRepository repository, AlunoRepository alunoRepository) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    protected JpaRepository<Curso, Long> getRepository() {
        return this.repository;
    }

    public ResponseEntity<Curso> save(CursoDtoRequest body) {
        System.out.println("lista de ids" + body.alunos());
        List<Aluno> allAlunos = this.alunoRepository.findAllById(body.alunos());

        Curso curso = new Curso(body.nome(), body.descricao(), allAlunos);
        return ResponseEntity.ok(this.repository.save(curso));

    }
}
