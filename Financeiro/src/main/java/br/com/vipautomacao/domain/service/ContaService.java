package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.exception.ContaNaoEncontradoException;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.repository.ContaRepository;

@Service
public class ContaService {


	private static final String MSG_CONTA_EM_USO 
		= "Conta de código %d não pode ser removido, pois está em uso";
	@Autowired
	private ContaRepository contaRepository;


	@Transactional
	public Conta salvar(Conta objeto) {
		return contaRepository.save(objeto);
	}


	@Transactional
	public void excluir(Integer contaId) {
		try {
			contaRepository.deleteById(contaId);
			contaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ContaNaoEncontradoException(contaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CONTA_EM_USO, contaId));
		}
	}


	public Conta buscarOuFalhar(Integer contaId) {
		return contaRepository.findById(contaId)
			.orElseThrow(() -> new ContaNaoEncontradoException(contaId));
	}
}
