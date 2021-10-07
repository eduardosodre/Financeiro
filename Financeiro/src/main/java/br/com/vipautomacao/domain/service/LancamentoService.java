package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.exception.LancamentoNaoEncontradoException;
import br.com.vipautomacao.domain.model.Lancamento;
import br.com.vipautomacao.domain.repository.LancamentoRepository;

@Service
public class LancamentoService {


	private static final String MSG_LANCAMENTO_EM_USO 
		= "Lancamento de código %d não pode ser removido, pois está em uso";
	@Autowired
	private LancamentoRepository lancamentoRepository;


	@Transactional
	public Lancamento salvar(Lancamento objeto) {
		return lancamentoRepository.save(objeto);
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
