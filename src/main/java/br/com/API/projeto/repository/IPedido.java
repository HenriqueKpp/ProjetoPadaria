package br.com.API.projeto.repository;

import br.com.API.projeto.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedido extends JpaRepository<Pedido, Integer> {
}
