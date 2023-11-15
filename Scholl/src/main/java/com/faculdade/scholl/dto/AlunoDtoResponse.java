package com.faculdade.scholl.dto;

import com.faculdade.scholl.model.Curso;

import java.util.List;

public record AlunoDtoResponse(Long id, String nome, String idade, String matricula, List<Curso> cursos) {
}
