package com.faculdade.scholl.dto;

import java.util.List;

public record AlunoDtoRequest(String nome, String idade, String matricula, List<String> idCursos) {
}
