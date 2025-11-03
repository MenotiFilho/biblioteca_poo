package br.com.fatec.biblioteca.biblioteca_poo.model.entity;

import jakarta.persistence.Entity;

@Entity
public class Cliente  extends Usuario{
    private int limiteEmprestimo;
    private String email;
    private String telefone;

    public Cliente() {
        this.limiteEmprestimo = 5;
    }

    public int getLimiteEmprestimo() {
        return limiteEmprestimo;
    }

    public void setLimiteEmprestimo(int limiteEmprestimo) {
        this.limiteEmprestimo = limiteEmprestimo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

