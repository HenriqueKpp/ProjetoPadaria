package br.com.API.projeto.repository;

import br.com.API.projeto.model.VendasPorDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVendasPorDia extends JpaRepository<VendasPorDia, String> {
}
