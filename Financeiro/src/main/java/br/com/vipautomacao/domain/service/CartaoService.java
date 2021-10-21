package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.CartaoNaoEncontradoException;
import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.model.Cartao;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.repository.CartaoRepository;

@Service
public class CartaoService {


	private static final String MSG_CARTAO_EM_USO 
		= "Cartao de código %d não pode ser removido, pois está em uso";
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private InstituicaoFinanceiraService instituicaoService;
	
	@Autowired
	private UsuarioService usuarioService;


	@Transactional
	public Cartao salvar(Cartao cartao) {
		Conta conta = contaService.buscarOuFalhar(cartao.getContaPagamento().getCodigo());
		InstituicaoFinanceira instituicao = instituicaoService.buscarOuFalhar(cartao.getInstituicao().getCodigo());
		Usuario usuario = usuarioService.buscarOuFalhar(cartao.getUsuario().getCodigo());
		
		cartao.setContaPagamento(conta);
		cartao.setInstituicao(instituicao);
		cartao.setUsuario(usuario);
		
		
		return cartaoRepository.save(cartao);
	}


	@Transactional
	public void excluir(Integer cartaoId) {
		try {
			cartaoRepository.deleteById(cartaoId);
			cartaoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new CartaoNaoEncontradoException(cartaoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CARTAO_EM_USO, cartaoId));
		}
	}


	public Cartao buscarOuFalhar(Integer cartaoId) {
		return cartaoRepository.findById(cartaoId)
			.orElseThrow(() -> new CartaoNaoEncontradoException(cartaoId));
	}
}
