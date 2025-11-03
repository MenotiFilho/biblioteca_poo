package br.com.fatec.biblioteca.biblioteca_poo.controller;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Cliente;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.ClienteRepository;
import br.com.fatec.biblioteca.biblioteca_poo.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listaClientes(Model model, @RequestParam(value = "query", required = false) String query) {
        List<Cliente> listaClientes;
        if (query != null && !query.trim().isEmpty()){
            listaClientes = usuarioService.buscarCliente(query);
        } else {
            listaClientes = usuarioService.listarTodosClientes();
        }
        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("queryAtual", query);
        return "clientes/list";
    }

    @GetMapping("/buscar")
    public String buscarClientesFragment(Model model, @RequestParam(value = "query", required = false) String query) {
        List<Cliente> listaClientes;
        if (query != null && !query.trim().isEmpty()) {
            listaClientes = usuarioService.buscarCliente(query);
        } else  {
            listaClientes = usuarioService.listarTodosClientes();
        }
        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("queryAtual", query);
        return "clientes/list :: tabelaResultados";
    }
    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }
    @PostMapping
    public String salvarCliente(@ModelAttribute("cliente") Cliente cliente) {
        usuarioService.salvarCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Cliente cliente = usuarioService.buscarClientePorId(id);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "clientes/form";
        }
        return "redirect:/clientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        usuarioService.deletarCliente(id);
        return "redirect:/clientes";
    }

}
