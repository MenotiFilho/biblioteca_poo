package br.com.fatec.biblioteca.biblioteca_poo.controller;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Cliente;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.ItemAcervo;
import br.com.fatec.biblioteca.biblioteca_poo.model.service.AcervoService;
import br.com.fatec.biblioteca.biblioteca_poo.model.service.EmprestimoService;
import br.com.fatec.biblioteca.biblioteca_poo.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private AcervoService acervoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/novo/{livroId}")
    public String mostrarFormularioEmprestimo(@PathVariable Long livroId, Model model) {

        ItemAcervo item = acervoService.buscarPorId(livroId);

        if (item == null || item.getQuantidadeDisponivel() <= 0) {
            return "redirect:/livros?error=Item não disponível";
        }

        model.addAttribute("item", item);

        return "emprestimos/form";
    }

    @PostMapping("/realizar")
    public String realizarEmprestimo(@RequestParam Long itemId,
                                     @RequestParam Long clienteId,
                                     RedirectAttributes redirectAttributes) {

        try {
            emprestimoService.realizarEmprestimo(itemId, clienteId);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Empréstimo realizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: " + e.getMessage());
        }

        return "redirect:/livros";
    }

    @GetMapping("/buscar-cliente")
    public String buscarClienteFragment(Model model,
                                        @RequestParam(value = "query", required = false) String query) {

        List<Cliente> listaClientes;
        if (query != null && !query.trim().isEmpty()) {
            listaClientes = usuarioService.buscarCliente(query);
        } else {
            listaClientes = List.of();
        }

        model.addAttribute("listaClientes", listaClientes);

        return "emprestimos/form :: search-results";
    }
    @GetMapping("/cliente/{clienteId}")
    public String mostrarHistoricoCliente(@PathVariable Long clienteId, Model model){
        Cliente cliente = usuarioService.buscarClientePorId(clienteId);
        if (cliente == null) {
            return "redirect:/livros?error=Cliente não encontrado";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("listaEmprestimos", emprestimoService.buscarPorCliente(clienteId));

        return  "emprestimos/list-por-cliente";
    }
    @PostMapping("/devolver")
    public String realizarDevolucao(@RequestParam Long emprestimoId,
                                    @RequestParam Long clienteId,
                                    RedirectAttributes redirectAttributes) {

        try {
            emprestimoService.realizarDevolucao(emprestimoId);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Devolução registrada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao devolver: " + e.getMessage());
        }

        return "redirect:/emprestimos/cliente/" + clienteId;
    }
}