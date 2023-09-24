package org.example.application.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contato {


    private Long id;
    private String nome;
    private String apelido;
    private Date data_nascimento;

    public Contato(Long id, String nome, String apelido, Date data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.data_nascimento = data_nascimento;
    }

    public Long getId() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataNascimentoStr = (data_nascimento != null) ? dateFormat.format(data_nascimento) : "N/A";

        return "Id: " + id +
                " | Nome: " + nome  +
                " | Apelido: " + apelido  +
                " | Data de Nascimento: " + dataNascimentoStr;
    }

}
