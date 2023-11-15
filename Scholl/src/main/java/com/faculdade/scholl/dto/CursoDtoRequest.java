package com.faculdade.scholl.dto;

import java.util.List;

public record CursoDtoRequest(String nome, String descricao, List<Long> alunos) {
}
