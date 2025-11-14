package br.com.API.projeto.repository;

import br.com.API.projeto.model.TotalVendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITotalVendas extends JpaRepository<TotalVendas, Integer> {
}
