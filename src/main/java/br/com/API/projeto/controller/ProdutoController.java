package br.com.API.projeto.controller;

import br.com.API.projeto.model.Produto;
import br.com.API.projeto.repository.IProduto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin("*")
public class ProdutoController{

    @Autowired
    private IProduto dao;

    @GetMapping("/produtos")
    @ResponseBody
    public ResponseEntity<List<Produto>> listaProduto (){
        List <Produto> lista =  dao.findAll();
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping("/produtos")
    @ResponseBody
    public ResponseEntity<Produto> criarProdutoApi(@RequestBody Produto produtoNovoInfos){
        Produto produtoNovo = dao.save(produtoNovoInfos);
        return ResponseEntity.status(201).body(produtoNovo);
    }

    @PostMapping("/produtos/criar")
    public String criarProdutoForm(@Valid @ModelAttribute("produto") Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "produtos/criar"; // Retorna para a página do formulário se houver erros
        }
        dao.save(produto);
        return "redirect:/produtos-page";
    }

    @GetMapping("/produtos/editar")
    public String showEditPage(Model model, @RequestParam int id) {
        Optional<Produto> produtoOpt = dao.findById(id);
        if (produtoOpt.isPresent()) {
            model.addAttribute("produto", produtoOpt.get());
            return "produtos/editar";
        } else {
            return "redirect:/produtos-page";
        }
    }

    @PostMapping("/produtos/editar")
    public String editarProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "produtos/editar";
        }
        dao.save(produto);
        return "redirect:/produtos-page";
    }

    @DeleteMapping("/produtos/{id}")
    @ResponseBody
    public ResponseEntity<?> excluirProduto(@PathVariable Integer id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
