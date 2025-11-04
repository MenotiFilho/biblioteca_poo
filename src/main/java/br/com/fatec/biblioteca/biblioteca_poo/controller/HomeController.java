package br.com.fatec.biblioteca.biblioteca_poo.controller;

import br.com.fatec.biblioteca.biblioteca_poo.model.service.AcervoService;
import br.com.fatec.biblioteca.biblioteca_poo.model.service.EmprestimoService;
import br.com.fatec.biblioteca.biblioteca_poo.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AcervoService acervoService;

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/")
    public String index(Model model) {

        long totalClientes = usuarioService.countTotalClientes();
        long totalItens = acervoService.countTotalItens();
        long totalAtivos = emprestimoService.countEmprestimosAtivos();

        model.addAttribute("totalClientes", totalClientes);
        model.addAttribute("totalItens", totalItens);
        model.addAttribute("totalEmprestimosAtivos", totalAtivos);

        return "index";
    }
}