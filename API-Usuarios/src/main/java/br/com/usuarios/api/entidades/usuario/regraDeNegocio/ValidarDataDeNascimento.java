package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

import br.com.usuarios.api.entidades.usuario.DadosCadastroUsuario;
import br.com.usuarios.api.entidades.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Component
public class ValidarDataDeNascimento implements RegraDeNegocioCadastro {



    @Override
    public void validar(DadosCadastroUsuario dados) {
        LocalDate dataNascimento = dados.dataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoje = LocalDate.now();
        int idade = Period.between(dataNascimento, hoje).getYears();

        if (idade < 12) {
            throw new RegraDeNegocioException("Usuário deve ter pelo menos 12 anos para ser cadastrado.");
        }
    }
}
