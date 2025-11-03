package br.com.fatec.biblioteca.biblioteca_poo.model.repository;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE " +
            "LOWER(c.nome) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.telefone) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Cliente> buscarClientePorMultiplosCampos(@Param("query") String query);
}
