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
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> lista = dao.findAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedidoNovoInfos) {
        if (pedidoNovoInfos.getItens() == null || pedidoNovoInfos.getItens().isEmpty()) {
            return ResponseEntity.badRequest().body("Pedido precisa ter ao menos 1 item.");
        }

        pedidoNovoInfos.getItens().forEach(item -> item.setPedido(pedidoNovoInfos));

        Pedido pedidoSalvo = dao.save(pedidoNovoInfos);
        dao.flush();
        pedidoSalvo = dao.findById(pedidoSalvo.getPedido_id()).get();

        return ResponseEntity.status(201).body(pedidoSalvo);
    }
}
