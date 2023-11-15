package com.faculdade.scholl.model;

//ID: Identificador único do aluno.
//Nome: Nome do aluno.
//Idade: Idade do aluno.
//Matrícula: Número de matrícula ou identificação na instituição.


import com.faculdade.scholl.jackson.CursoSerializer;
import com.faculdade.scholl.jackson.ListCursoDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table
@EqualsAndHashCode(of = "id")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String idade;
    private String matricula;


    @ManyToMany(mappedBy = "alunos")
    @JsonIgnore
    private List<Curso> cursos;

    public Aluno(Long id, String nome, String idade, String matricula, List<Curso> cursos) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
        if (cursos == null) {
            this.cursos = new ArrayList<>();
        }
    }
}
