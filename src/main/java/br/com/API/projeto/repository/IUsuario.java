package br.com.API.projeto.repository;
import br.com.API.projeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuario extends JpaRepository<Usuario, Integer>  {



}
