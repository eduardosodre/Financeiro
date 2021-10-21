package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.exception.LancamentoNaoEncontradoException;
import br.com.vipautomacao.domain.exception.NegocioException;
import br.com.vipautomacao.domain.model.Categoria;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.model.Lancamento;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.repository.LancamentoRepository;

@Service
public class LancamentoService {


	private static final String MSG_LANCAMENTO_EM_USO 
		= "Lancamento de código %d não pode ser removido, pois está em uso";
	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private ContaService contaService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		Categoria categoria = categoriaService.buscarOuFalhar(lancamento.getCategoria().getCodigo());
		Conta conta1 = contaService.buscarOuFalhar(lancamento.getConta().getCodigo());
		Conta conta2 = null;
		if(lancamento.getConta2() != null) {
			conta2 = contaService.buscarOuFalhar(lancamento.getConta2().getCodigo());
		}
		Usuario usuario = usuarioService.buscarOuFalhar(lancamento.getUsuario().getCodigo());
		
		
		Integer usuarioId = lancamento.getUsuario().getCodigo();
		Integer usuarioCategoria = null;
		if(categoria.getUsuario() != null) {
			usuarioCategoria = categoria.getUsuario().getCodigo();
		}
		Integer usuarioConta1 = conta1.getUsuario().getCodigo();
		Integer usuarioConta2 = null;
		if(conta2 != null) {
			usuarioConta2 = conta2.getUsuario().getCodigo();
		}
		
		lancamento.setCategoria(categoria);
		lancamento.setConta(conta1);
		lancamento.setConta2(conta2);
		lancamento.setUsuario(usuario);
		
		if(usuarioCategoria != null && !usuarioId.equals(usuarioCategoria)) {
			throw new NegocioException(String.format("Categoria Inexistente para o Usuário"));
		}else if(!usuarioId.equals(usuarioConta1)) {
			throw new NegocioException(String.format("Conta Inexistente para o Usuário"));
		}else if(usuarioConta2 != null && !usuarioId.equals(usuarioConta2)) {
			throw new NegocioException(String.format("Conta Destino Inexistente para o Usuário"));
		}
		
		if(lancamento.getPago()) {
			conta1.ajustarSaldo(lancamento, lancamento.getValor());
			if(conta2 != null) {
				conta2.ajustarSaldo(lancamento, lancamento.getValor());
			}
		}
		
			
		return lancamentoRepository.save(lancamento);
	}
	
	@Transactional
	public Lancamento atualizarLancamento(Lancamento antigo, Lancamento lancamento) {
		Categoria categoria = categoriaService.buscarOuFalhar(lancamento.getCategoria().getCodigo());
		Conta conta1 = contaService.buscarOuFalhar(lancamento.getConta().getCodigo());
		Conta conta2 = null;
		if(lancamento.getConta2() != null) {
			conta2 = contaService.buscarOuFalhar(lancamento.getConta2().getCodigo());
		}
		
		Integer usuario = lancamento.getUsuario().getCodigo();
		Integer usuarioCategoria = null;
		if(categoria.getUsuario() != null) {
			usuarioCategoria = categoria.getUsuario().getCodigo();
		}
		Integer usuarioConta1 = conta1.getUsuario().getCodigo();
		Integer usuarioConta2 = null;
		if(conta2 != null) {
			usuarioConta2 = conta2.getUsuario().getCodigo();
		}
		
		lancamento.setCategoria(categoria);
		lancamento.setConta(conta1);
		lancamento.setConta2(conta2);
		
		if(usuarioCategoria != null && !usuario.equals(usuarioCategoria)) {
			throw new NegocioException(String.format("Categoria Inexistente para o Usuário"));
		}else if(!usuario.equals(usuarioConta1)) {
			throw new NegocioException(String.format("Conta Inexistente para o Usuário"));
		}else if(usuarioConta2 != null && !usuario.equals(usuarioConta2)) {
			throw new NegocioException(String.format("Conta Destino Inexistente para o Usuário"));
		}
		
		
		
		if(antigo != null) {
			if(antigo.getPago()) {
				Conta contaAntiga1 = antigo.getConta();
				contaAntiga1.ajustarSaldo(antigo, antigo.getValor()*-1);
				
				Conta contaAntiga2 = null;
				if(antigo.getConta2() != null) {
					contaAntiga2 = antigo.getConta2();
					contaAntiga2.ajustarSaldo(antigo, antigo.getValor()*-1);
				}
			}
		}
		
		Double valorLancar = lancamento.getValor();
		if(lancamento.getPago()) {
			conta1.ajustarSaldo(lancamento, valorLancar);
			if(conta2 != null) {
				conta2.ajustarSaldo(lancamento, valorLancar);
			}
		}
		
		
		return lancamentoRepository.save(lancamento);
	}


	@Transactional
	public void excluir(Integer lancamentoId) {
		try {
			lancamentoRepository.deleteById(lancamentoId);
			lancamentoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new LancamentoNaoEncontradoException(lancamentoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_LANCAMENTO_EM_USO, lancamentoId));
		}
	}


	public Lancamento buscarOuFalhar(Integer lancamentoId) {
		return lancamentoRepository.findById(lancamentoId)
			.orElseThrow(() -> new LancamentoNaoEncontradoException(lancamentoId));
	}
}
