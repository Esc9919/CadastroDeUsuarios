package br.com.usuarios.api.entidades.usuario;

public record DadosListagemUsuario(Long id, String nome, String cpf, String telefone) {

    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getTelefone());
    }
}
