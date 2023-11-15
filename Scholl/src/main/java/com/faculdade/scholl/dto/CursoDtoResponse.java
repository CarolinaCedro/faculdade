package com.faculdade.scholl.dto;

import com.faculdade.scholl.model.Aluno;

import java.util.List;

public record CursoDtoResponse(Long id, String nome, String descricao, List<Aluno> alunos) {
}
