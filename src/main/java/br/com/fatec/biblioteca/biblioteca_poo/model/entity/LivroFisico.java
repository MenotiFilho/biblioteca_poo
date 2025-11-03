package br.com.fatec.biblioteca.biblioteca_poo.model.entity;

import jakarta.persistence.Entity;

@Entity
public class LivroFisico extends ItemAcervo{

    private int edicao;
    private String isbn;

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
