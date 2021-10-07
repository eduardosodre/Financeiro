package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.exception.InstituicaoFinanceiraNaoEncontradoException;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;
import br.com.vipautomacao.domain.repository.InstituicaoFinanceiraRepository;

@Service
public class InstituicaoFinanceiraService {


	private static final String MSG_INSTITUICAOFINANCEIRA_EM_USO 
		= "InstituicaoFinanceira de código %d não pode ser removido, pois está em uso";
	@Autowired
	private InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;


	@Transactional
	public InstituicaoFinanceira salvar(InstituicaoFinanceira objeto) {
		return instituicaoFinanceiraRepository.save(objeto);
	}


	@Transactional
	public void excluir(Integer instituicaoFinanceiraId) {
		try {
			instituicaoFinanceiraRepository.deleteById(instituicaoFinanceiraId);
			instituicaoFinanceiraRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new InstituicaoFinanceiraNaoEncontradoException(instituicaoFinanceiraId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_INSTITUICAOFINANCEIRA_EM_USO, instituicaoFinanceiraId));
		}
	}


	public InstituicaoFinanceira buscarOuFalhar(Integer instituicaoFinanceiraId) {
		return instituicaoFinanceiraRepository.findById(instituicaoFinanceiraId)
			.orElseThrow(() -> new InstituicaoFinanceiraNaoEncontradoException(instituicaoFinanceiraId));
	}
}
