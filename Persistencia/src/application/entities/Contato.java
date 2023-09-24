package application.entities;

import java.io.Serializable;

public class Contato implements Serializable {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


