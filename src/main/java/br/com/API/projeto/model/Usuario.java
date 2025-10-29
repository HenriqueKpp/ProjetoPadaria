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
@Table(name= "usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DIZ Q VAI GERAR O ID AUTOMATICO
    @Column(name = "id")
    private int ID;

    @Size(min = 3, max = 255 , message = "O nome deve ter entre 3 e 255 caracteres!")
    //@NotBlank(message = "O nome é obrigatório!") //NAO PERMITE SO ESPAÇOS EM BRANCO
    @Column(name = "nome", length = 255, nullable = false) // tamanho e not null
    private String nome;

   // @CPF(message = "CPF invalido !")
    @NotBlank(message = "O CPF é obrigatório!")
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @NotBlank(message = "A senha é obrigatória!")
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    //@NotBlank(message = "O telefone é obrigatório!")
    @Column(name = "telefone", length = 25, nullable = false)
    private String telefone;


}
