package br.com.API.projeto.controller;

import br.com.API.projeto.model.VendasPorDia;
import br.com.API.projeto.repository.IVendasPorDia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/vendas_por_dia")
public class VendasPorDiaController {

    @Autowired
    private IVendasPorDia dao;

    @GetMapping
    public ResponseEntity<List<VendasPorDia>> listarVendasPorDia() {
        List<VendasPorDia> lista = dao.findAll();
        return ResponseEntity.ok(lista);
    }
}
