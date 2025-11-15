package br.com.API.projeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    // ROTA: GET /ping
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
