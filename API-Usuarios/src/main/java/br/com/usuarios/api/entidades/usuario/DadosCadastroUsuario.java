package br.com.usuarios.api.entidades.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,

        @NotNull
        Date dataNascimento,

        @NotBlank
        String cpf,

        @NotBlank
        String telefone,

        @NotBlank
        String userName,

        @NotBlank
        String senha,

        @NotBlank
        String email,

        @NotNull
        Categoria categoria

) {
}
