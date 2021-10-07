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
import br.com.vipautomacao.api.v1.assembler.banco.BancoInputDisassembler;
import br.com.vipautomacao.api.v1.assembler.banco.BancoModelAssembler;
import br.com.vipautomacao.api.v1.model.banco.BancoModel;
import br.com.vipautomacao.api.v1.modelinput.banco.BancoInput;
import br.com.vipautomacao.api.v1.openapi.controller.banco.BancoControllerOpenApi;
import br.com.vipautomacao.domain.model.Banco;
import br.com.vipautomacao.domain.repository.BancoRepository;
import br.com.vipautomacao.domain.filter.BancoFilter;
import br.com.vipautomacao.domain.service.BancoService;
import br.com.vipautomacao.domain.service.query.BancoQueryService;

@RestController
@RequestMapping(path = "/v1/bancos", produces = MediaType.APPLICATION_JSON_VALUE)
public class BancoController implements BancoControllerOpenApi {
	@Autowired
	private BancoRepository bancoRepository;

	@Autowired
	private BancoService cadastroBanco;

	@Autowired
	private BancoModelAssembler bancoModelAssembler;

	@Autowired
	private BancoInputDisassembler bancoInputDisassembler;

	//// @Autowired
	//// private PagedResourcesAssembler<Banco> pagedResourcesAssembler;

	@Autowired
	private BancoQueryService bancoQuery;

	
	@Override
	@GetMapping
	public Page<BancoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Banco> bancosPage = bancoRepository.findAll(pageable);

		List<BancoModel> bancosModel = bancoModelAssembler.toCollectionModel(bancosPage.getContent());

		Page<BancoModel> bancosModelPage = new PageImpl<>(bancosModel, pageable, bancosPage.getTotalElements());

		return bancosModelPage;
	}

	@Override
	@GetMapping("/{bancoId}")
	public BancoModel buscar(@PathVariable Integer bancoId) {
		Banco banco = cadastroBanco.buscarOuFalhar(bancoId);

		return bancoModelAssembler.toModel(banco);
	}

	@CheckSecurity.Banco.PodeGerenciar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BancoModel adicionar(@RequestBody @Valid BancoInput bancoInput) {
		Banco banco = bancoInputDisassembler.toDomainObject(bancoInput);
		banco = cadastroBanco.salvar(banco);

		return bancoModelAssembler.toModel(banco);
	}

	@CheckSecurity.Banco.PodeGerenciar
	@Override
	@PutMapping("/{bancoId}")
	public BancoModel atualizar(@PathVariable Integer bancoId, @RequestBody @Valid BancoInput bancoInput) {
		Banco bancoAtual = cadastroBanco.buscarOuFalhar(bancoId);
		bancoInputDisassembler.copyToDomainObject(bancoInput, bancoAtual);
		bancoAtual = cadastroBanco.salvar(bancoAtual);

		return bancoModelAssembler.toModel(bancoAtual);
	}

	@CheckSecurity.Banco.PodeGerenciar
	@Override
	@DeleteMapping("/{bancoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer bancoId) {
		cadastroBanco.excluir(bancoId);
	}

	
	@Override
	@GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<BancoModel> consultaPorFiltro(BancoFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset,
			@PageableDefault(size = 10) Pageable pageable) {
		List<Banco> lista = bancoQuery.consultaPorFiltro(filtro, timeOffset, pageable);
		// Page<Banco> bancosPage = lista;

		List<BancoModel> bancosModel = bancoModelAssembler.toCollectionModel(lista);

		Page<BancoModel> bancosModelPage = new PageImpl<>(bancosModel, pageable, lista.size());

		return bancosModelPage;
	}

}
