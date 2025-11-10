package br.com.API.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.API.projeto.repository.IUsuario;
import br.com.API.projeto.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @GetMapping("/info")
    public ResponseEntity <List <Usuario>> listaUsuario (){
      List <Usuario> lista =  dao.findAll();
        return ResponseEntity.status(200).body(lista);
    }

//CRUD - UPLOAD - POST - CREATE USER
    @PostMapping
    public ResponseEntity <Usuario> CriarUsuario(@Valid @RequestBody Usuario usuarioNovoInfos){
        String encoder = this.passwordEncoder.encode(usuarioNovoInfos.getSenha());
        usuarioNovoInfos.setSenha(encoder);
        Usuario usuarioNovo = dao.save(usuarioNovoInfos);
        return ResponseEntity.status(201).body(usuarioNovo);
    }
//CRUD - UPDATE
    @PutMapping
    public ResponseEntity <Usuario>  AtualizarUsuario(@Valid @RequestBody Usuario usuarioAtualizadoInfos){
        String encoder = this.passwordEncoder.encode(usuarioAtualizadoInfos.getSenha());
        usuarioAtualizadoInfos.setSenha(encoder);
        Usuario usuarioAtualizado = dao.save(usuarioAtualizadoInfos);
        return ResponseEntity.status(201).body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Usuario> validarSenha(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioBanco = dao.findByCpf(usuario.getCpf());
        if (usuarioBanco == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean valid = passwordEncoder.matches(usuario.getSenha(), usuarioBanco.getSenha());
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBanco);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExeption(MethodArgumentNotValidException Ex){
        Map<String, String> errors = new HashMap<>();

        Ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }

}



