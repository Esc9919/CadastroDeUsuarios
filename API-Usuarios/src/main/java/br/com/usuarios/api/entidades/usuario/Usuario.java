package br.com.usuarios.api.entidades.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String telefone;
    private String userName;
    private String senha;
    private String email;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean ativo;


    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.userName = dados.userName();
        this.senha = dados.senha();
        this.email = dados.email();
        this.categoria = dados.categoria();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }

        if (dados.userName() != null){
            this.userName = dados.userName();
        }

        if (dados.senha() != null){
            this.senha = dados.senha();
        }

        if (dados.email() != null){
            this.email = dados.email();
        }
    }

    public void desativar() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }
}
