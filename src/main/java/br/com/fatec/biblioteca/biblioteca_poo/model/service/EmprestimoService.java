package br.com.fatec.biblioteca.biblioteca_poo.model.service;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Cliente;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Emprestimo;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.ItemAcervo;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private AcervoService acervoService;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Emprestimo realizarEmprestimo(Long itemId, Long clienteId){
        ItemAcervo item = acervoService.buscarPorId(itemId);
        Cliente cliente = usuarioService.buscarClientePorId(clienteId);

        if (item == null) {
            throw new IllegalArgumentException("Item não encontrado.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        if (item.getQuantidadeDisponivel() <= 0) {
            throw new IllegalArgumentException("Item não disponível para empréstimo.");
        }

        item.setQuantidadeDisponivel(item.getQuantidadeDisponivel()-1);
        acervoService.salvar(item);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setItemAcervo(item);
        emprestimo.setCliente(cliente);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(14));

        return emprestimoRepository.save(emprestimo);
    }
    public List<Emprestimo> listaTodos(){
        return emprestimoRepository.findAll();
    }
}
