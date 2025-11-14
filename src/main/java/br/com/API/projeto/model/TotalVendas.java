package br.com.API.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "total_vendas_view")
public class TotalVendas {

    @Id
    @Column(name = "dummy_id")
    private Integer dummyId;

    @Column(name = "total_vendido")
    private Double totalVendido;
}

