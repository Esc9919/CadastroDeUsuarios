package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

import br.com.usuarios.api.entidades.usuario.DadosAtualizacaoUsuario;
import br.com.usuarios.api.entidades.usuario.DadosCadastroUsuario;
import br.com.usuarios.api.entidades.usuario.Usuario;
import br.com.usuarios.api.entidades.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AtualizarSenhaExistente implements RegraDeNegocioAtualizacao {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void validar(DadosAtualizacaoUsuario dados) {
        List<Usuario> usuarios = repository.findAll();

        Usuario usuario = repository.findById(dados.id())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));

        if (usuario.getSenha().equals(dados.senha())){
            throw new RegraDeNegocioException("A nova senha tem que ser diferente da atual");
        }
    }
}
