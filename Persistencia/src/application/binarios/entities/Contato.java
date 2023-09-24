package application.binarios.entities;

import java.io.Serializable;

class Contato implements Serializable {
    private String nome;
    private String numeroTelefone;
    private String email;

    public Contato(String nome, String numeroTelefone, String email) {
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nNúmero de Telefone: " + numeroTelefone + "\nE-mail: " + email + "\n";
    }
}


