package br.com.API.projeto.service;

import br.com.API.projeto.model.Usuario;
import br.com.API.projeto.repository.IUsuario;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    private IUsuario repository;


    public UsuarioService(IUsuario repositoriy) {
        this.repository = repositoriy;
    }


    public List<Usuario> listarUsuario(){
        List<Usuario> lista = repository.findAll();
        return lista;
    }

    public Usuario criarUsuario(Usuario usuario){
        Usuario usuarioNovo= repository.save(usuario);
        return usuarioNovo;
    }

}
