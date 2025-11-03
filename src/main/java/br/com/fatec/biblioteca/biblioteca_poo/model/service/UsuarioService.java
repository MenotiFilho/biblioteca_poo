package br.com.fatec.biblioteca.biblioteca_poo.model.service;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Cliente;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Usuario;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.ClienteRepository;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("O nome do cliente é obrigatório");
        }
        return clienteRepository.save(cliente);
    }
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarCliente(String query) {
        return clienteRepository.buscarClientePorMultiplosCampos(query);
    }

}