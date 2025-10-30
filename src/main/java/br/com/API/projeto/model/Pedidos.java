package br.com.API.projeto.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDateTime;



@Data

@Entity
@Table(name = "Pedido")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DIZ Q VAI GERAR O ID AUTOMATICO
    @Column(name = "pedido_id")
    private int pedido_id;

    @Column(name = "valor", length = 255, nullable = false) // tamanho e not null
    private float valor;

    @Column(name = "data_pedido", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataPedido;

    @Column(name = "forma_de_pagamento", length = 255, nullable = false) // tamanho e not null
    private String forma_de_pagamento;

    @Column(name = "cpf_cliente", length = 11, nullable = false) // tamanho e not null
    private int cpf_cliente;




}
