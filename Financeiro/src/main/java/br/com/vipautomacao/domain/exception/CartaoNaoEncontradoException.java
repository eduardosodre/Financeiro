package br.com.vipautomacao.domain.exception;

public class CartaoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CartaoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CartaoNaoEncontradoException(Integer cartaoId) {
		this(String.format("Não existe um cadastro de Cartao com código %d", cartaoId));
	}
	
}
