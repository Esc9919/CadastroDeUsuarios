package br.com.usuarios.api.controller;

import br.com.usuarios.api.entidades.usuario.*;
import br.com.usuarios.api.entidades.usuario.regraDeNegocio.RegraDeNegocioAtualizacao;
import br.com.usuarios.api.entidades.usuario.regraDeNegocio.RegraDeNegocioCadastro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    private List<RegraDeNegocioAtualizacao> regraDeNegocioAtualizacao;

    @Autowired
    private List<RegraDeNegocioCadastro> regraDeNegocioCadastros;

    @PostMapping
    @Transactional
    public ResponseEntity inserir(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = new Usuario(dados);
        regraDeNegocioCadastros.forEach(v -> v.validar(dados));
        repository.save(usuario);
        URI uri = uriBuilder.path("/usuarios{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        Page page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados){
        Usuario usuario = repository.getReferenceById(dados.id());
        regraDeNegocioAtualizacao.forEach(v -> v.validar(dados));
        usuario.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity dasativar(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.desativar();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity reativar(@PathVariable Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.reativar();
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }


}
