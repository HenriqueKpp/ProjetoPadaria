package br.com.API.projeto.controller;

import br.com.API.projeto.model.Produto;
import br.com.API.projeto.model.Usuario;
import br.com.API.projeto.repository.IProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")


public class ProdutoController{

    @Autowired
    private IProduto dao;

    @GetMapping
    public ResponseEntity<List<Produto>> listaProduto (){
        List <Produto> lista =  dao.findAll();
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Produto> CriarProduto(@RequestBody Produto produtoNovoInfos){
        Produto produtoNovo = dao.save(produtoNovoInfos);
        return ResponseEntity.status(201).body(produtoNovo);
    }













}
