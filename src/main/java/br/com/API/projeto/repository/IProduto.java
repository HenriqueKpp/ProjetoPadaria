package br.com.API.projeto.repository;

import br.com.API.projeto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProduto extends JpaRepository<Produto, Integer> {
    Produto findByNome(String nome);
}
