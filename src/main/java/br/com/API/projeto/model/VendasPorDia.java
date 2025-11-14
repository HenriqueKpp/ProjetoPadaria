package br.com.API.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vendas_por_dia")
public class VendasPorDia {

    @Id
    @Column(name = "dia")
    private String dia; // Pode ser String ou LocalDate

    @Column(name = "total_vendido")
    private Double totalVendido;
}
