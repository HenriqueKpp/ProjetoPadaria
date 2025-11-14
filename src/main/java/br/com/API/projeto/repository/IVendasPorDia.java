package br.com.API.projeto.repository;

import br.com.API.projeto.model.VendasPorDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
// Alterado o tipo do ID de String para LocalDate
public interface IVendasPorDia extends JpaRepository<VendasPorDia, LocalDate> {

    // Busca vendas em um intervalo de datas, ordenado da mais recente para a mais antiga
    List<VendasPorDia> findByDiaBetweenOrderByDiaDesc(LocalDate inicio, LocalDate fim);

    // Busca vendas a partir de uma data de início
    List<VendasPorDia> findByDiaGreaterThanEqualOrderByDiaDesc(LocalDate inicio);

    // Busca vendas até uma data de fim
    List<VendasPorDia> findByDiaLessThanEqualOrderByDiaDesc(LocalDate fim);

    // Busca todas as vendas, ordenadas da mais recente para a mais antiga
    List<VendasPorDia> findAllByOrderByDiaDesc();
}
