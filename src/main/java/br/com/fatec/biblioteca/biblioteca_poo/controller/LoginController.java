package br.com.fatec.biblioteca.biblioteca_poo.controller;

import br.com.fatec.biblioteca.biblioteca_poo.model.entity.Bibliotecario;
import br.com.fatec.biblioteca.biblioteca_poo.model.repository.BibliotecarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @PostMapping("/auth-login")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {

        // 2. BUSCAR O USUÁRIO NO BANCO PELO LOGIN
        Optional<Bibliotecario> optBibliotecario = bibliotecarioRepository.findByLogin(username);

        // 3. VERIFICAR SE O USUÁRIO EXISTE E SE A SENHA BATE
        if (optBibliotecario.isPresent() && optBibliotecario.get().getSenha().equals(password)) {

            // SUCESSO: Salva o NOME do usuário na sessão (para o dashboard)
            session.setAttribute("usuarioLogado", optBibliotecario.get().getNome());
            return "redirect:/";

        } else {
            // FALHA: Redireciona de volta com erro
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}