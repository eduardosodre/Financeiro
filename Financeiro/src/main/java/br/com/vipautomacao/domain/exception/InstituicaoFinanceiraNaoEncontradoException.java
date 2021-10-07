package br.com.vipautomacao.domain.exception;

public class InstituicaoFinanceiraNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public InstituicaoFinanceiraNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public InstituicaoFinanceiraNaoEncontradoException(Integer instituicaoFinanceiraId) {
		this(String.format("Não existe um cadastro de InstituicaoFinanceira com código %d", instituicaoFinanceiraId));
	}
	
}
