package br.com.fatec.biblioteca.biblioteca_poo.model.repository;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.ItemAcervo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAcervoRepository extends JpaRepository<ItemAcervo, Long> {

    @Query("SELECT i FROM ItemAcervo i WHERE " +
            "TYPE(i) = LivroFisico AND (" +
            "LOWER(i.titulo) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(i.autor) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(TREAT(i AS LivroFisico).isbn) LIKE LOWER(CONCAT('%', :query, '%'))" +
            ")")
    List<ItemAcervo> buscarPorTituloAutorOuIsbn(@Param("query") String query);
}
