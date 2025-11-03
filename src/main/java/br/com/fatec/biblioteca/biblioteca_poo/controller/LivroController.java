package br.com.fatec.biblioteca.biblioteca_poo.controller;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.ItemAcervo;
import br.com.fatec.biblioteca.biblioteca_poo.model.entity.LivroFisico;
import br.com.fatec.biblioteca.biblioteca_poo.model.service.AcervoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private AcervoService acervoService;

    /**
     * MÉTODO 1: Carrega a PÁGINA INTEIRA (Layout + Conteúdo)
     * Responde ao GET /livros
     */
    @GetMapping
    public String listarLivros(Model model,
                               @RequestParam(value = "query", required = false) String query) {

        // Chama a lógica de busca refatorada
        buscarEAdicionarResultados(model, query);

        // Retorna o template da PÁGINA COMPLETA
        return "livros/list";
    }

    /**
     * MÉTODO 2 (NOVO): Carrega SÓ O FRAGMENTO DA TABELA (para o HTMX)
     * Responde ao GET /livros/buscar
     */
    @GetMapping("/buscar")
    public String buscarLivrosFragment(Model model,
                                       @RequestParam(value = "query", required = false) String query) {

        // Chama a MESMA lógica de busca
        buscarEAdicionarResultados(model, query);

        // Retorna SÓ o FRAGMENTO da tabela (nome do template :: nome do fragmento)
        return "livros/list :: tabelaResultados";
    }

    /**
     * MÉTODO 3 (NOVO - PRIVADO): Lógica de busca refatorada (DRY)
     * Para não repetir o código nos dois métodos acima.
     */
    private void buscarEAdicionarResultados(Model model, String query) {
        List<ItemAcervo> listaLivros;

        if (query != null && !query.trim().isEmpty()) {
            // Se tem uma query, busca por ela
            listaLivros = acervoService.buscarAcervo(query);
        } else {
            // Se não tem query, lista todos
            listaLivros = acervoService.listarTodos();
        }

        model.addAttribute("listaLivros", listaLivros);
        model.addAttribute("queryAtual", query); // Envia a query de volta (para o input não limpar)
    }

    /**
     * Interface Inserção (QAP) - Parte A: Mostrar o formulário
     * Responde ao GET /livros/novo
     */
    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("livro", new LivroFisico());
        return "livros/form";
    }

    /**
     * Interface Inserção/Alteração (QAP) - Parte B: Salvar os dados
     * Responde ao POST /livros
     */
    @PostMapping
    public String salvarLivro(@ModelAttribute("livro") LivroFisico livro) {
        acervoService.salvar(livro);
        return "redirect:/livros";
    }

    /**
     * Interface Alteração (QAP) - Parte A: Carregar o formulário
     * Responde ao GET /livros/editar/{id}
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        ItemAcervo livro = acervoService.buscarPorId(id);

        if (livro != null) {
            model.addAttribute("livro", livro);
            return "livros/form"; // Reutiliza o form.html
        } else {
            return "redirect:/livros";
        }
    }

    /**
     * Interface Exclusão (QAP)
     * Responde ao GET /livros/deletar/{id}
     */
    @GetMapping("/deletar/{id}")
    public String deletarLivro(@PathVariable Long id) {
        acervoService.deletar(id);
        return "redirect:/livros";
    }
}