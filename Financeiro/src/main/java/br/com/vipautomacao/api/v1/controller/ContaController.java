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
import br.com.vipautomacao.api.v1.assembler.conta.ContaInputDisassembler;
import br.com.vipautomacao.api.v1.assembler.conta.ContaModelAssembler;
import br.com.vipautomacao.api.v1.model.conta.ContaModel;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaInput;
import br.com.vipautomacao.api.v1.openapi.controller.conta.ContaControllerOpenApi;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.repository.ContaRepository;
import br.com.vipautomacao.domain.filter.ContaFilter;
import br.com.vipautomacao.domain.service.ContaService;
import br.com.vipautomacao.domain.service.query.ContaQueryService;
import br.com.vipautomacao.domain.exception.BancoNaoEncontradoException;
import br.com.vipautomacao.domain.exception.UsuarioNaoEncontradoException;
import br.com.vipautomacao.domain.exception.NegocioException;



@RestController
@RequestMapping(path = "/v1/contas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContaController implements ContaControllerOpenApi {
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaService cadastroConta;
	
	@Autowired
	private ContaModelAssembler contaModelAssembler;
	
	@Autowired
	private ContaInputDisassembler contaInputDisassembler;

	////@Autowired
	////private PagedResourcesAssembler<Conta> pagedResourcesAssembler;
 

	@Autowired
	private ContaQueryService contaQuery;
	
		@CheckSecurity.Conta.PodeConsultar
	@Override
	@GetMapping
	public Page<ContaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Conta> contasPage = contaRepository.findAll(pageable);
		
		List<ContaModel> contasModel = contaModelAssembler
				.toCollectionModel(contasPage.getContent());
		
		Page<ContaModel> contasModelPage = new PageImpl<>(contasModel, pageable, 
				contasPage.getTotalElements());
		
		return contasModelPage;
	}
	@Override
	@GetMapping("/{contaId}")
	public ContaModel buscar(@PathVariable Integer contaId) {
		Conta conta = cadastroConta.buscarOuFalhar(contaId);
		
		return contaModelAssembler.toModel(conta);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContaModel adicionar(@RequestBody @Valid ContaInput contaInput) {
		Conta conta = contaInputDisassembler.toDomainObject(contaInput);
		conta = cadastroConta.salvar(conta);
		
		return contaModelAssembler.toModel(conta);
	}
	@Override
	@PutMapping("/{contaId}")
	public ContaModel atualizar(@PathVariable Integer contaId,
			@RequestBody @Valid ContaInput contaInput) {
		Conta contaAtual = cadastroConta.buscarOuFalhar(contaId);
		contaInputDisassembler.copyToDomainObject(contaInput, contaAtual);
		contaAtual = cadastroConta.salvar(contaAtual);
		
		return contaModelAssembler.toModel(contaAtual);
	}
	@Override
	@DeleteMapping("/{contaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer contaId) {
		cadastroConta.excluir(contaId);
	}


	@Override
	@GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ContaModel> consultaPorFiltro(ContaFilter filtro, 
		@RequestParam(required = false, defaultValue = "+00:00") String timeOffset, 
		@PageableDefault(size = 10) Pageable pageable) {
		List<Conta> lista = contaQuery.consultaPorFiltro(filtro, timeOffset, pageable);
		//Page<Conta> contasPage = lista;
		
		List<ContaModel> contasModel = contaModelAssembler
				.toCollectionModel(lista);
		
		Page<ContaModel> contasModelPage = new PageImpl<>(contasModel, pageable, 
				lista.size());
		
		return contasModelPage;
	}
	
}
