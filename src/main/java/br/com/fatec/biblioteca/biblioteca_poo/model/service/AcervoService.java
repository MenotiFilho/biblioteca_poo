package br.com.fatec.biblioteca.biblioteca_poo.model.service;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.ItemAcervo;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.ItemAcervoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcervoService {

    @Autowired
    private ItemAcervoRepository itemAcervoRepository;

    public List<ItemAcervo> listarTodos() {
        return itemAcervoRepository.findAll();
    }

    public ItemAcervo salvar(ItemAcervo item) {
        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do item não pode ser nulo ou vazio.");
        }

        if (item.getId() == null) {
            item.setQuantidadeDisponivel(item.getQuantidadeTotal());
        }

        return itemAcervoRepository.save(item);
    }

    public ItemAcervo buscarPorId(Long id) {
        return itemAcervoRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        itemAcervoRepository.deleteById(id);
    }

    public List<ItemAcervo> buscarAcervo(String query) {
        return itemAcervoRepository.buscarPorTituloAutorOuIsbn(query);
    }
}