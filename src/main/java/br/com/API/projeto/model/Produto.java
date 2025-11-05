package br.com.API.projeto.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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


    @NotNull(message = "O preço-custo é obrigatório!")
    @Column(name = "preco_custo", nullable = false) // tamanho e not null
    private float precoCusto;


    @NotNull(message = "O preço de venda é obrigatório!")
    @Column(name = "preco_venda", nullable = false) // tamanho e not null
    private float precoVenda;


    @NotNull(message = "A quantidade de estoque é obrigatória!")
    @Min(value = 0, message = "A quantidade não pode ser negativa!")
    @Column(name = "qntd_estoque", nullable = false) // tamanho e not null
    private int qntdEstoque;



}
