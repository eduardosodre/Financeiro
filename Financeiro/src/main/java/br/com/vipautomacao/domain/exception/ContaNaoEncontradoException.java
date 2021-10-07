package br.com.vipautomacao.domain.exception;

public class ContaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ContaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ContaNaoEncontradoException(Integer contaId) {
		this(String.format("Não existe um cadastro de Conta com código %d", contaId));
	}
	
}
