package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

import br.com.usuarios.api.entidades.usuario.DadosCadastroUsuario;

public interface RegraDeNegocioCadastro {
    public void validar(DadosCadastroUsuario dados);
}
