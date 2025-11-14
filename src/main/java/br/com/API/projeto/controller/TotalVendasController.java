package br.com.API.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotalVendasController {

        @Autowired
        private JdbcTemplate jdbc;

        @GetMapping("/api/total-vendas")
        public Double getTotalVendido() {
                String sql = "SELECT total_vendido FROM total_vendas_view";
                Double resultado = jdbc.queryForObject(sql, Double.class);

                return resultado != null ? resultado : 0.0;
        }
}


