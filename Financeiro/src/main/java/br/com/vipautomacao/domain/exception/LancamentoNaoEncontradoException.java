package br.com.vipautomacao.domain.exception;

public class LancamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public LancamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public LancamentoNaoEncontradoException(Integer lancamentoId) {
		this(String.format("Não existe um cadastro de Lancamento com código %d", lancamentoId));
	}
	
}
