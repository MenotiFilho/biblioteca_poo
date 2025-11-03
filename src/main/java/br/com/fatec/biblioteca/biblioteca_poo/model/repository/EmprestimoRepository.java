package br.com.fatec.biblioteca.biblioteca_poo.model.repository;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
