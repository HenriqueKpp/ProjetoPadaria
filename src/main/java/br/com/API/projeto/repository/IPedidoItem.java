package br.com.API.projeto.repository;

import br.com.API.projeto.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoItem extends JpaRepository<PedidoItem, Integer> {
}
