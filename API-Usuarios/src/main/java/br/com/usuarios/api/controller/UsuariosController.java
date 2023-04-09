package br.com.usuarios.api.controller;

import br.com.usuarios.api.entidades.usuario.DadosCadastroUsuarios;
import br.com.usuarios.api.entidades.usuario.DadosDetalhamentoUsuario;
import br.com.usuarios.api.entidades.usuario.Usuario;
import br.com.usuarios.api.entidades.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity inserir(@RequestBody @Valid DadosCadastroUsuarios dados, UriComponentsBuilder uriBuilder){

        Usuario usuario = new Usuario(dados);
        repository.save(usuario);

        URI uri = uriBuilder.path("/usuarios{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }
}
