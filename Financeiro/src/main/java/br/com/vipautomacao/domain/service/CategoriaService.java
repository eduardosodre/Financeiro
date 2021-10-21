package br.com.vipautomacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipautomacao.domain.exception.EntidadeEmUsoException;
import br.com.vipautomacao.domain.exception.CategoriaNaoEncontradoException;
import br.com.vipautomacao.domain.model.Categoria;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {


	private static final String MSG_CATEGORIA_EM_USO 
		= "Categoria de código %d não pode ser removido, pois está em uso";
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioService usuarioService;


	@Transactional
	public Categoria salvar(Categoria objeto) {
		Usuario usuario = usuarioService.buscarOuFalhar(objeto.getUsuario().getCodigo());
		objeto.setUsuario(usuario);
		return categoriaRepository.save(objeto);
	}


	@Transactional
	public void excluir(Integer categoriaId) {
		try {
			categoriaRepository.deleteById(categoriaId);
			categoriaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new CategoriaNaoEncontradoException(categoriaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CATEGORIA_EM_USO, categoriaId));
		}
	}


	public Categoria buscarOuFalhar(Integer categoriaId) {
		return categoriaRepository.findById(categoriaId)
			.orElseThrow(() -> new CategoriaNaoEncontradoException(categoriaId));
	}
}
