package br.com.API.projeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    // Landing Page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    //Dashboard

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/produtos-page") // Usando um caminho diferente para não conflitar com a API
    public String produtos() {
        return "produtos";
    }

    @GetMapping("/pedidos-page") // Usando um caminho diferente para não conflitar com a API
    public String pedidos() {
        return "pedidos";
    }


    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios";
    }
}