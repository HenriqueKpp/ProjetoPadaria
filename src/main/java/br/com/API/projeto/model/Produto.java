package br.com.API.projeto.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name = "produto")
public class Produto {

private int id;

private String nome;

private float preco_custo;

private float preco_venda;

private int qntd_estoque;



}
