package br.com.vipautomacao.api.v1.controller;

import java.time.OffsetDateTime;
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

import br.com.vipautomacao.api.v1.assembler.categoria.CategoriaInputDisassembler;
import br.com.vipautomacao.api.v1.assembler.categoria.CategoriaModelAssembler;
import br.com.vipautomacao.api.v1.model.categoria.CategoriaModel;
import br.com.vipautomacao.api.v1.modelinput.categoria.CategoriaInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
import br.com.vipautomacao.api.v1.openapi.controller.categoria.CategoriaControllerOpenApi;
import br.com.vipautomacao.core.security.CheckSecurity;
import br.com.vipautomacao.core.security.VipSecurity;
import br.com.vipautomacao.domain.exception.ContaNaoEncontradoException;
import br.com.vipautomacao.domain.filter.CategoriaFilter;
import br.com.vipautomacao.domain.model.Categoria;
import br.com.vipautomacao.domain.model.EPermissao;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.service.CategoriaService;
import br.com.vipautomacao.domain.service.query.CategoriaQueryService;

@RestController
@RequestMapping(path = "/v1/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController implements CategoriaControllerOpenApi {
	
	@Autowired
	private VipSecurity vipSecurity;
	
	@Autowired
	private CategoriaService cadastroCategoria;

	@Autowired
	private CategoriaModelAssembler categoriaModelAssembler;

	@Autowired
	private CategoriaInputDisassembler categoriaInputDisassembler;

	@Autowired
	private CategoriaQueryService categoriaQuery;

	@CheckSecurity.Categoria.PodeConsultar
	@Override
	@GetMapping
	public Page<CategoriaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Integer usuarioId = vipSecurity.getUsuarioId();
		if (vipSecurity.hasAuthority(EPermissao.GERENCIAR_CATEGORIA.getDescricao())) {
			usuarioId = null;
		}
		List<Categoria> lista = categoriaQuery.consultaPorUsuario(usuarioId, pageable);

		List<CategoriaModel> categoriasModel = categoriaModelAssembler.toCollectionModel(lista);

		Page<CategoriaModel> categoriasModelPage = new PageImpl<>(categoriasModel, pageable, lista.size());


		return categoriasModelPage;
	}

	@CheckSecurity.Categoria.PodeBuscar
	@Override
	@GetMapping("/{categoriaId}")
	public CategoriaModel buscar(@PathVariable Integer categoriaId) {
		Categoria categoria = cadastroCategoria.buscarOuFalhar(categoriaId);

		if(categoria.getCancelado()) {
			throw new ContaNaoEncontradoException(categoriaId);
		}
		return categoriaModelAssembler.toModel(categoria);
	}

	@CheckSecurity.Categoria.PodeCriar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaModel adicionar(@RequestBody @Valid CategoriaInput categoriaInput) {
		Categoria categoria = categoriaInputDisassembler.toDomainObject(categoriaInput);
		categoria.setUsuario(new Usuario());
		categoria.getUsuario().setCodigo(vipSecurity.getUsuarioId());
		categoria.setCancelado(false);
		categoria = cadastroCategoria.salvar(categoria);

		return categoriaModelAssembler.toModel(categoria);
	}

	@CheckSecurity.Categoria.PodeEditar
	@Override
	@PutMapping("/{categoriaId}")
	public CategoriaModel atualizar(@PathVariable Integer categoriaId,
			@RequestBody @Valid CategoriaInput categoriaInput) {
		Categoria categoriaAtual = cadastroCategoria.buscarOuFalhar(categoriaId);
		
		if(categoriaAtual.getCancelado()) {
			throw new ContaNaoEncontradoException(categoriaId);
		}
		
		categoriaInput.setUsuario(new UsuarioIdInput());
		categoriaInput.getUsuario().setCodigo(vipSecurity.getUsuarioId());
		
		categoriaInputDisassembler.copyToDomainObject(categoriaInput, categoriaAtual);
		categoriaAtual = cadastroCategoria.salvar(categoriaAtual);

		return categoriaModelAssembler.toModel(categoriaAtual);
	}

	@CheckSecurity.Categoria.PodeEditar
	@Override
	@DeleteMapping("/{categoriaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer categoriaId) {
		Categoria categoriaAtual = cadastroCategoria.buscarOuFalhar(categoriaId);
		
		if(categoriaAtual.getCancelado()) {
			throw new ContaNaoEncontradoException(categoriaId);
		}
		
		categoriaAtual.setCancelado(true);
		categoriaAtual.setDataCancel(OffsetDateTime.now());
		cadastroCategoria.salvar(categoriaAtual);
	}

	@Override
	@GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<CategoriaModel> consultaPorFiltro(CategoriaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset,
			@PageableDefault(size = 10) Pageable pageable) {
		Integer usuarioId = vipSecurity.getUsuarioId();
		if (vipSecurity.hasAuthority(EPermissao.GERENCIAR_CATEGORIA.getDescricao())) {
			usuarioId = null;
		}
		if(usuarioId != null) {
			filtro.setUsuario(usuarioId);
		}
		List<Categoria> lista = categoriaQuery.consultaPorFiltro(filtro, timeOffset, pageable);

		List<CategoriaModel> categoriasModel = categoriaModelAssembler.toCollectionModel(lista);

		Page<CategoriaModel> categoriasModelPage = new PageImpl<>(categoriasModel, pageable, lista.size());

		return categoriasModelPage;
	}

}
