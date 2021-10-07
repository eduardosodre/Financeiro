package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.exception.BancoNaoEncontradoException;
import br.com.vipautomacao.domain.model.Banco;
import br.com.vipautomacao.domain.repository.BancoRepository;

@Service
public class BancoService {


	private static final String MSG_BANCO_EM_USO 
		= "Banco de código %d não pode ser removido, pois está em uso";
	@Autowired
	private BancoRepository bancoRepository;


	@Transactional
	public Banco salvar(Banco objeto) {
		return bancoRepository.save(objeto);
	}


	@Transactional
	public void excluir(Integer bancoId) {
		try {
			bancoRepository.deleteById(bancoId);
			bancoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new BancoNaoEncontradoException(bancoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_BANCO_EM_USO, bancoId));
		}
	}


	public Banco buscarOuFalhar(Integer bancoId) {
		return bancoRepository.findById(bancoId)
			.orElseThrow(() -> new BancoNaoEncontradoException(bancoId));
	}
}
