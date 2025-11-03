package br.com.API.projeto.controller;

import br.com.API.projeto.repository.IProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")


public class ProdutoController{

    @Autowired
    private IProduto dao;














}
