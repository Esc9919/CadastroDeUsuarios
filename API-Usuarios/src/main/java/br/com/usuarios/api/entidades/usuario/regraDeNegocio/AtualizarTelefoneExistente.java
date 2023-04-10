package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

import br.com.usuarios.api.entidades.usuario.DadosAtualizacaoUsuario;
import br.com.usuarios.api.entidades.usuario.Usuario;
import br.com.usuarios.api.entidades.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AtualizarTelefoneExistente implements RegraDeNegocioAtualizacao {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void validar(DadosAtualizacaoUsuario dados) {
        List<String> telefones = new ArrayList<>();
        List<Usuario> usuarios = repository.findAll();

        for (Usuario usuario : usuarios) {
            telefones.add(usuario.getTelefone());
        }

        if (telefones.contains(dados.telefone())) {
            throw new RegraDeNegocioException("Telefone já existe no sistema");
        }
    }
}
