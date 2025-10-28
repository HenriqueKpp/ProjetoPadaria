package br.com.API.projeto.controller;

import java.util.List;

import br.com.API.projeto.repository.IUsuario;
import br.com.API.projeto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//classe de ending point
@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")

public class UsuarioController  {

    @Autowired //PERMITE USAR OS METODOS DA INTERFACE SEM DAR O IMPLEMENTS
    private IUsuario dao;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//CRUD - REQUEST - SOLICITA AS INFOS
    @GetMapping
    public ResponseEntity <List <Usuario>> listaUsuario (){
      List <Usuario> lista =  dao.findAll();
        return ResponseEntity.status(200).body(lista);
    }

//CRUD - UPLOAD - POST - CREATE USER
    @PostMapping
    public ResponseEntity <Usuario> CriarUsuario(@RequestBody Usuario usuarioNovoInfos){
        String encoder = this.passwordEncoder.encode(usuarioNovoInfos.getSenha());
        usuarioNovoInfos.setSenha(encoder);
        Usuario usuarioNovo = dao.save(usuarioNovoInfos);
        return ResponseEntity.status(201).body(usuarioNovo);
    }
//CRUD - UPDATE
    @PutMapping
    public ResponseEntity <Usuario>  AtualizarUsuario(@RequestBody Usuario usuarioAtualizadoInfos){
        String encoder = this.passwordEncoder.encode(usuarioAtualizadoInfos.getSenha());
        usuarioAtualizadoInfos.setSenha(encoder);
        Usuario usuarioAtualizado = dao.save(usuarioAtualizadoInfos);
        return ResponseEntity.status(201).body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>excluirUsuario (@PathVariable Integer id){
        dao.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> validarSenha(@RequestBody Usuario usuario){
    String senha  = dao.getById(usuario.getID()).getSenha();
    Boolean valid = passwordEncoder.matches(usuario.getSenha(),senha);
    if(!valid){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //INAUTORIZADO
    }
    else return ResponseEntity.status(200).build(); //AUTORIZADO/ SENHA CERTA
    }



}



