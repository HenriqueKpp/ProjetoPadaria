package br.com.API.projeto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Integer pedido_id;

    @Column(name = "valor_final")
    private Double valor_final;

    @Column(name = "funcionario_id", nullable = false, length = 11)
    private String funcionario_id;

    @Column(name = "cpf_cliente", length = 11)
    private String cpf_cliente;

    @Column(name = "forma_pagamento", nullable = false, length = 255)
    private String forma_pagamento;

    @Column(name = "data_pedido", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime data_pedido = LocalDateTime.now();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> itens;
}
