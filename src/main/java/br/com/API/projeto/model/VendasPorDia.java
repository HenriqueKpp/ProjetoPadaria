package br.com.API.projeto.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Data
@Entity
@Immutable // Indica que a entidade é imutável (uma visão de banco de dados)
@Table(name = "vendas_por_dia")
public class VendasPorDia {

    @Id
    @Column(name = "dia")
    private LocalDate dia; // Alterado de String para LocalDate

    @Column(name = "total_vendido")
    private Double totalVendido;
}
