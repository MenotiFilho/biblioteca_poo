package br.com.fatec.biblioteca.biblioteca_poo.model.repository;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.ItemAcervo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAcervoRepository extends JpaRepository<ItemAcervo, Long> {
    List<ItemAcervo> findByTituloContainingIgnoreCase(String query);
}
