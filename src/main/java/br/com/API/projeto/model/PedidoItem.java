package br.com.API.projeto.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "pedido_item")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_id")
    private Integer pedido_item_id;

    // Relacionamento com Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    // Relacionamento com Produto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    // O preco_unitario e subtotal serão preenchidos pelo MySQL via trigger
    @Column(name = "preco_unitario", insertable = false, updatable = false)
    private Float preco_unitario;

    @Column(name = "subtotal", insertable = false, updatable = false)
    private Float subtotal;
}
