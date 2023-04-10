package br.com.usuarios.api.entidades.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

        @NotNull
        Long id,
        String telefone,
        String userName,
        String senha,
        String email
) {
}
