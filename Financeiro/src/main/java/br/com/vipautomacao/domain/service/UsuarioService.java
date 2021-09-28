package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.exception.UsuarioNaoEncontradoException;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {


	private static final String MSG_USUARIO_EM_USO 
		= "Usuario de código %d não pode ser removido, pois está em uso";
	@Autowired
	private UsuarioRepository usuarioRepository;


	@Transactional
	public Usuario salvar(Usuario objeto) {
		return usuarioRepository.save(objeto);
	}


	@Transactional
	public void excluir(Integer usuarioId) {
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(usuarioId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_USUARIO_EM_USO, usuarioId));
		}
	}


	public Usuario buscarOuFalhar(Integer usuarioId) {
		return usuarioRepository.findById(usuarioId)
			.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
}
