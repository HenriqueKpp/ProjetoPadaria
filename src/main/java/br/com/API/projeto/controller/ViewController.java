package br.com.API.projeto.controller;

import br.com.API.projeto.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

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

    //Dashboard

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/produtos-page") // Usando um caminho diferente para não conflitar com a API
    public String produtos() {
        return "/produtos/produtos";
    }

    @GetMapping("/produtos/criar")
    public String showCreatePage(Model model) {
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "produtos/criar";
    }

    @GetMapping("/pedidos-page")
    public String pedidos() {
        return "pedidos";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios";
    }

    @GetMapping("/total-vendas")
    public String totalVendasPage() {
        return "total-vendas";
    }
}