package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

import br.com.usuarios.api.entidades.usuario.DadosCadastroUsuario;
import br.com.usuarios.api.entidades.usuario.Usuario;
import br.com.usuarios.api.entidades.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CadastrarCpfExistente implements RegraDeNegocioCadastro {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void validar(DadosCadastroUsuario dados) {
        List<String> cpfs = new ArrayList<>();
        List<Usuario> usuarios = repository.findAll();

        for (Usuario usuario : usuarios) {
            cpfs.add(usuario.getCpf());
        }

        if (cpfs.contains(dados.cpf())) {
            throw new RegraDeNegocioException("CPF já cadastrado");
        }
    }
}
