package br.com.vipautomacao.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vipautomacao.api.v1.assembler.usuario.UsuarioInputDisassembler;
import br.com.vipautomacao.api.v1.assembler.usuario.UsuarioModelAssembler;
import br.com.vipautomacao.api.v1.model.usuario.UsuarioModel;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioInput;
import br.com.vipautomacao.api.v1.openapi.controller.usuario.UsuarioControllerOpenApi;
import br.com.vipautomacao.domain.filter.UsuarioFilter;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.repository.UsuarioRepository;
import br.com.vipautomacao.domain.service.UsuarioService;
import br.com.vipautomacao.domain.service.query.UsuarioQueryService;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService cadastroUsuario;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;

//	@Autowired
//	private PagedResourcesAssembler<Usuario> pagedResourcesAssembler;

	@Autowired
	private UsuarioQueryService usuarioQuery;

	//@CheckSecurity.Usuario.PodeConsultar
	@Override
	@GetMapping
	public Page<UsuarioModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Usuario> usuariosPage = usuarioRepository.findAll(pageable);

		List<UsuarioModel> usuariosModel = usuarioModelAssembler.toCollectionModel(usuariosPage.getContent());

		Page<UsuarioModel> usuariosModelPage = new PageImpl<>(usuariosModel, pageable, usuariosPage.getTotalElements());

		return usuariosModelPage;
	}

	@Override
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Integer usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		return usuarioModelAssembler.toModel(usuario);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		usuario = cadastroUsuario.salvar(usuario);

		return usuarioModelAssembler.toModel(usuario);
	}

	@Override
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Integer usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = cadastroUsuario.salvar(usuarioAtual);

		return usuarioModelAssembler.toModel(usuarioAtual);
	}

	@Override
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer usuarioId) {
		cadastroUsuario.excluir(usuarioId);
	}

	@Override
	@GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<UsuarioModel> consultaPorFiltro(UsuarioFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset,
			@PageableDefault(size = 10) Pageable pageable) {
		List<Usuario> lista = usuarioQuery.consultaPorFiltro(filtro, timeOffset, pageable);
		// Page<Usuario> usuariosPage = lista;

		List<UsuarioModel> usuariosModel = usuarioModelAssembler.toCollectionModel(lista);

		Page<UsuarioModel> usuariosModelPage = new PageImpl<>(usuariosModel, pageable, lista.size());

		return usuariosModelPage;
	}

}
