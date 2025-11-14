package br.com.API.projeto.controller;

import br.com.API.projeto.model.TotalVendas;
import br.com.API.projeto.model.VendasPorDia;
import br.com.API.projeto.repository.ITotalVendas;
import br.com.API.projeto.repository.IVendasPorDia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TotalVendasController {

        @Autowired
        private ITotalVendas totalVendasRepository;

        @Autowired
        private IVendasPorDia vendasPorDiaRepository;

        @GetMapping("/api/total-vendas")
        public ResponseEntity<Double> getTotalVendas() {
                List<TotalVendas> totais = totalVendasRepository.findAll();
                if (totais.isEmpty() || totais.get(0) == null) {
                        return ResponseEntity.ok(0.0);
                }
                return ResponseEntity.ok(totais.get(0).getTotalVendido());
        }

        @GetMapping("/vendas_por_dia")
        public ResponseEntity<List<VendasPorDia>> getVendasPorDia(
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

                List<VendasPorDia> resultado;

                if (inicio != null && fim != null) {
                        resultado = vendasPorDiaRepository.findByDiaBetweenOrderByDiaDesc(inicio, fim);
                } else if (inicio != null) {
                        resultado = vendasPorDiaRepository.findByDiaGreaterThanEqualOrderByDiaDesc(inicio);
                } else if (fim != null) {
                        resultado = vendasPorDiaRepository.findByDiaLessThanEqualOrderByDiaDesc(fim);
                } else {
                        resultado = vendasPorDiaRepository.findAllByOrderByDiaDesc();
                }

                return ResponseEntity.ok(resultado);
        }
}
