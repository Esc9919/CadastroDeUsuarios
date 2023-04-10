package br.com.usuarios.api.entidades.usuario.regraDeNegocio;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException(String mensagem){
        super(mensagem);
    }
}
