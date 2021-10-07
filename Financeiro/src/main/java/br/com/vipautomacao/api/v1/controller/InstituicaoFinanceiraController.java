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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.web.PagedResourcesAssembler;
import br.com.vipautomacao.core.security.CheckSecurity;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.vipautomacao.api.v1.assembler.instituicaofinanceira.InstituicaoFinanceiraInputDisassembler;
import br.com.vipautomacao.api.v1.assembler.instituicaofinanceira.InstituicaoFinanceiraModelAssembler;
import br.com.vipautomacao.api.v1.model.instituicaofinanceira.InstituicaoFinanceiraModel;
import br.com.vipautomacao.api.v1.modelinput.instituicaofinanceira.InstituicaoFinanceiraInput;
import br.com.vipautomacao.api.v1.openapi.controller.instituicaofinanceira.InstituicaoFinanceiraControllerOpenApi;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;
import br.com.vipautomacao.domain.repository.InstituicaoFinanceiraRepository;
import br.com.vipautomacao.domain.filter.InstituicaoFinanceiraFilter;
import br.com.vipautomacao.domain.service.InstituicaoFinanceiraService;
import br.com.vipautomacao.domain.service.query.InstituicaoFinanceiraQueryService;

@RestController
@RequestMapping(path = "/v1/instituicaofinanceiras", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstituicaoFinanceiraController implements InstituicaoFinanceiraControllerOpenApi {
	@Autowired
	private InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

	@Autowired
	private InstituicaoFinanceiraService cadastroInstituicaoFinanceira;

	@Autowired
	private InstituicaoFinanceiraModelAssembler instituicaoFinanceiraModelAssembler;

	@Autowired
	private InstituicaoFinanceiraInputDisassembler instituicaoFinanceiraInputDisassembler;

	//// @Autowired
	//// private PagedResourcesAssembler<InstituicaoFinanceira>
	//// pagedResourcesAssembler;

	@Autowired
	private InstituicaoFinanceiraQueryService instituicaoFinanceiraQuery;

	
	@Override
	@GetMapping
	public Page<InstituicaoFinanceiraModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<InstituicaoFinanceira> instituicaoFinanceirasPage = instituicaoFinanceiraRepository.findAll(pageable);

		List<InstituicaoFinanceiraModel> instituicaoFinanceirasModel = instituicaoFinanceiraModelAssembler
				.toCollectionModel(instituicaoFinanceirasPage.getContent());

		Page<InstituicaoFinanceiraModel> instituicaoFinanceirasModelPage = new PageImpl<>(instituicaoFinanceirasModel,
				pageable, instituicaoFinanceirasPage.getTotalElements());

		return instituicaoFinanceirasModelPage;
	}

	@Override
	@GetMapping("/{instituicaoFinanceiraId}")
	public InstituicaoFinanceiraModel buscar(@PathVariable Integer instituicaoFinanceiraId) {
		InstituicaoFinanceira instituicaoFinanceira = cadastroInstituicaoFinanceira
				.buscarOuFalhar(instituicaoFinanceiraId);

		return instituicaoFinanceiraModelAssembler.toModel(instituicaoFinanceira);
	}

	@CheckSecurity.InstituicaoFinanceira.PodeGerenciar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InstituicaoFinanceiraModel adicionar(
			@RequestBody @Valid InstituicaoFinanceiraInput instituicaoFinanceiraInput) {
		InstituicaoFinanceira instituicaoFinanceira = instituicaoFinanceiraInputDisassembler
				.toDomainObject(instituicaoFinanceiraInput);
		instituicaoFinanceira = cadastroInstituicaoFinanceira.salvar(instituicaoFinanceira);

		return instituicaoFinanceiraModelAssembler.toModel(instituicaoFinanceira);
	}

	@CheckSecurity.InstituicaoFinanceira.PodeGerenciar
	@Override
	@PutMapping("/{instituicaoFinanceiraId}")
	public InstituicaoFinanceiraModel atualizar(@PathVariable Integer instituicaoFinanceiraId,
			@RequestBody @Valid InstituicaoFinanceiraInput instituicaoFinanceiraInput) {
		InstituicaoFinanceira instituicaoFinanceiraAtual = cadastroInstituicaoFinanceira
				.buscarOuFalhar(instituicaoFinanceiraId);
		instituicaoFinanceiraInputDisassembler.copyToDomainObject(instituicaoFinanceiraInput,
				instituicaoFinanceiraAtual);
		instituicaoFinanceiraAtual = cadastroInstituicaoFinanceira.salvar(instituicaoFinanceiraAtual);

		return instituicaoFinanceiraModelAssembler.toModel(instituicaoFinanceiraAtual);
	}

	@CheckSecurity.InstituicaoFinanceira.PodeGerenciar
	@Override
	@DeleteMapping("/{instituicaoFinanceiraId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer instituicaoFinanceiraId) {
		cadastroInstituicaoFinanceira.excluir(instituicaoFinanceiraId);
	}

	@Override
	@GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<InstituicaoFinanceiraModel> consultaPorFiltro(InstituicaoFinanceiraFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset,
			@PageableDefault(size = 10) Pageable pageable) {
		List<InstituicaoFinanceira> lista = instituicaoFinanceiraQuery.consultaPorFiltro(filtro, timeOffset, pageable);
		// Page<InstituicaoFinanceira> instituicaoFinanceirasPage = lista;

		List<InstituicaoFinanceiraModel> instituicaoFinanceirasModel = instituicaoFinanceiraModelAssembler
				.toCollectionModel(lista);

		Page<InstituicaoFinanceiraModel> instituicaoFinanceirasModelPage = new PageImpl<>(instituicaoFinanceirasModel,
				pageable, lista.size());

		return instituicaoFinanceirasModelPage;
	}

}
