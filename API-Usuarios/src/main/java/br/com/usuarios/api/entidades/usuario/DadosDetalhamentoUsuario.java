package br.com.usuarios.api.entidades.usuario;

import java.util.Date;

public record DadosDetalhamentoUsuario(Long id, String nome, Date dataNascimento, String cpf, String telefone, String userName,
                                       String senha, String email, Categoria categoria) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getDataNascimento(), usuario.getCpf(), usuario.getTelefone(), usuario.getUserName(), usuario.getSenha(),
                usuario.getEmail(), usuario.getCategoria());
    }
}
