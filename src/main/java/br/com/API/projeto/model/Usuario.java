package br.com.API.projeto.model;
import jakarta.persistence.*;

@Entity
@Table(name= "usuario")
public class Usuario {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DIZ Q VAI GERAR O ID AUTOMATICO
    @Column(name = "id")
    private int ID;

    @Column(name = "nome", length = 255, nullable = false) // tamanho e not null
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "telefone", length = 25, nullable = false)
    private String telefone;



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
