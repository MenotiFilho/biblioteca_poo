package br.com.fatec.biblioteca.biblioteca_poo.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Emprestimo {

    private static final double VALOR_MULTA_DIARIA = 0.50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "item_acervo_id")
    private ItemAcervo itemAcervo;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;

    private LocalDate dataDevolucao;

    public double calcularMulta() {
        if (dataPrevistaDevolucao == null) {
            return 0.0;
        }

        LocalDate dataFinalParaCalculo;

        if (dataDevolucao != null) {
            dataFinalParaCalculo = dataDevolucao;
        } else {
            dataFinalParaCalculo = LocalDate.now();
        }

        if (dataFinalParaCalculo.isAfter(dataPrevistaDevolucao)) {
            long diasDeAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataFinalParaCalculo);
            return diasDeAtraso * VALOR_MULTA_DIARIA;
        }

        return 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemAcervo getItemAcervo() {
        return itemAcervo;
    }

    public void setItemAcervo(ItemAcervo itemAcervo) {
        this.itemAcervo = itemAcervo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate getDataDevolucao) {
        this.dataDevolucao = getDataDevolucao;
    }
}
