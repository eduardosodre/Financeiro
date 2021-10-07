package br.com.vipautomacao.domain.exception;

public class BancoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public BancoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public BancoNaoEncontradoException(Integer bancoId) {
		this(String.format("Não existe um cadastro de Banco com código %d", bancoId));
	}
	
}
