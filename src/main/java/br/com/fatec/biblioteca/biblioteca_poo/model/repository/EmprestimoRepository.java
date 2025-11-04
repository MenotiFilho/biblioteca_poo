package br.com.fatec.biblioteca.biblioteca_poo.model.repository;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByClienteId(Long clienteId);

    long countByDataDevolucaoIsNull();

    @Query("SELECT e FROM Emprestimo e WHERE e.cliente.id = :clienteId ORDER BY " +
            "CASE WHEN e.dataDevolucao IS NULL THEN 0 ELSE 1 END, " +
            "e.dataEmprestimo DESC")
    List<Emprestimo> buscarHistoricoOrdenado(@Param("clienteId") Long clienteId);
}
