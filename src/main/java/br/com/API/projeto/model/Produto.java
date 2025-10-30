package br.com.API.projeto.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;


@Data

@Entity
@Table(name = "produto")
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DIZ Q VAI GERAR O ID AUTOMATICO
    @Column(name = "id")
    private int id;


    @NotBlank(message = "O nome é obrigatório!")
    @Column(name = "nome", length = 255, nullable = false) // tamanho e not null
    private String nome;


    @NotBlank(message = "O preço-custo é obrigatório!")
    @Column(name = "preco_custo", nullable = false) // tamanho e not null
    private float preco_custo;


    @NotBlank(message = "O preço de venda é obrigatório!")
    @Column(name = "preco_venda", nullable = false) // tamanho e not null
    private float preco_venda;


    @NotBlank(message = "A quantidade de estoque é obrigatória!")
    @Column(name = "qntd_estoque", nullable = false) // tamanho e not null
    private int qntd_estoque;



}
