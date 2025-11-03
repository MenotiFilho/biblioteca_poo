package br.com.fatec.biblioteca.biblioteca_poo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmxTestController {

    @GetMapping("/htmx-teste")
    public String htmxTest() {
        return "fragments/teste-sucesso";
    }
}