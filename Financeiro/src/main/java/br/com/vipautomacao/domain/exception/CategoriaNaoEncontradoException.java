package br.com.vipautomacao.domain.exception;

public class CategoriaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriaNaoEncontradoException(Integer categoriaId) {
		this(String.format("Não existe um cadastro de Categoria com código %d", categoriaId));
	}
	
}
