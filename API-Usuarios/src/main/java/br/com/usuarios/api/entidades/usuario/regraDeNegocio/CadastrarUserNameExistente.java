package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

import br.com.usuarios.api.entidades.usuario.DadosCadastroUsuario;
import br.com.usuarios.api.entidades.usuario.Usuario;
import br.com.usuarios.api.entidades.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CadastrarUserNameExistente implements RegraDeNegocioCadastro {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void validar(DadosCadastroUsuario dados) {
        List<String> userName = new ArrayList<>();
        List<Usuario> usuarios = repository.findAll();

        for (Usuario usuario : usuarios) {
            userName.add(usuario.getUserName());
        }

        if (userName.contains(dados.userName())) {
            throw new RegraDeNegocioException("UserName já existente");
        }
    }
}
