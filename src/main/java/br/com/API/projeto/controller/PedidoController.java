package br.com.API.projeto.controller;

import br.com.API.projeto.model.Pedido;
import br.com.API.projeto.model.Produto;
import br.com.API.projeto.repository.IPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/pedidos")

public class PedidoController {

    @Autowired
    private IPedido dao;

    @GetMapping
    public ResponseEntity<List<Pedido>> listaPedido (){
        List <Pedido> lista =  dao.findAll();
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Pedido> CriarPedido(@RequestBody Pedido pedidoNovoInfos){
        Pedido pedidoNovo = dao.save(pedidoNovoInfos);
        return ResponseEntity.status(201).body(pedidoNovo);
    }


}
