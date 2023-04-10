package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

import br.com.usuarios.api.entidades.usuario.DadosAtualizacaoUsuario;
import br.com.usuarios.api.entidades.usuario.DadosCadastroUsuario;

public interface RegraDeNegocioAtualizacao {
    public void validar(DadosAtualizacaoUsuario dados);
}
