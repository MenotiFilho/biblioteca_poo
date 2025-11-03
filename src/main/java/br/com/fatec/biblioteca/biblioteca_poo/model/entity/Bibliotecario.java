package br.com.fatec.biblioteca.biblioteca_poo.model.entity;

import jakarta.persistence.Entity;

@Entity
public class Bibliotecario extends Usuario {

    private String login;
    private String senha;


    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}