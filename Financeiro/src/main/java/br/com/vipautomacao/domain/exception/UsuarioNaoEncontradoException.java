package br.com.vipautomacao.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Integer usuarioId) {
		this(String.format("Não existe um cadastro de Usuario com código %d", usuarioId));
	}
	
}
