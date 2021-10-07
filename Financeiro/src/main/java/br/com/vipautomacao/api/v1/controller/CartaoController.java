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
import br.com.vipautomacao.api.v1.assembler.cartao.CartaoInputDisassembler;
import br.com.vipautomacao.api.v1.assembler.cartao.CartaoModelAssembler;
import br.com.vipautomacao.api.v1.model.cartao.CartaoModel;
import br.com.vipautomacao.api.v1.modelinput.cartao.CartaoInput;
import br.com.vipautomacao.api.v1.openapi.controller.cartao.CartaoControllerOpenApi;
import br.com.vipautomacao.domain.model.Cartao;
import br.com.vipautomacao.domain.repository.CartaoRepository;
import br.com.vipautomacao.domain.filter.CartaoFilter;
import br.com.vipautomacao.domain.service.CartaoService;
import br.com.vipautomacao.domain.service.query.CartaoQueryService;
import br.com.vipautomacao.domain.exception.InstituicaoFinanceiraNaoEncontradoException;
import br.com.vipautomacao.domain.exception.ContaNaoEncontradoException;
import br.com.vipautomacao.domain.exception.UsuarioNaoEncontradoException;
import br.com.vipautomacao.domain.exception.NegocioException;



@RestController
@RequestMapping(path = "/v1/cartaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartaoController implements CartaoControllerOpenApi {
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CartaoService cadastroCartao;
	
	@Autowired
	private CartaoModelAssembler cartaoModelAssembler;
	
	@Autowired
	private CartaoInputDisassembler cartaoInputDisassembler;

	////@Autowired
	////private PagedResourcesAssembler<Cartao> pagedResourcesAssembler;
 

	@Autowired
	private CartaoQueryService cartaoQuery;
	
		@CheckSecurity.Cartao.PodeConsultar
	@Override
	@GetMapping
	public Page<CartaoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cartao> cartaosPage = cartaoRepository.findAll(pageable);
		
		List<CartaoModel> cartaosModel = cartaoModelAssembler
				.toCollectionModel(cartaosPage.getContent());
		
		Page<CartaoModel> cartaosModelPage = new PageImpl<>(cartaosModel, pageable, 
				cartaosPage.getTotalElements());
		
		return cartaosModelPage;
	}
	@Override
	@GetMapping("/{cartaoId}")
	public CartaoModel buscar(@PathVariable Integer cartaoId) {
		Cartao cartao = cadastroCartao.buscarOuFalhar(cartaoId);
		
		return cartaoModelAssembler.toModel(cartao);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CartaoModel adicionar(@RequestBody @Valid CartaoInput cartaoInput) {
		Cartao cartao = cartaoInputDisassembler.toDomainObject(cartaoInput);
		cartao = cadastroCartao.salvar(cartao);
		
		return cartaoModelAssembler.toModel(cartao);
	}
	@Override
	@PutMapping("/{cartaoId}")
	public CartaoModel atualizar(@PathVariable Integer cartaoId,
			@RequestBody @Valid CartaoInput cartaoInput) {
		Cartao cartaoAtual = cadastroCartao.buscarOuFalhar(cartaoId);
		cartaoInputDisassembler.copyToDomainObject(cartaoInput, cartaoAtual);
		cartaoAtual = cadastroCartao.salvar(cartaoAtual);
		
		return cartaoModelAssembler.toModel(cartaoAtual);
	}
	@Override
	@DeleteMapping("/{cartaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer cartaoId) {
		cadastroCartao.excluir(cartaoId);
	}


	@Override
	@GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<CartaoModel> consultaPorFiltro(CartaoFilter filtro, 
		@RequestParam(required = false, defaultValue = "+00:00") String timeOffset, 
		@PageableDefault(size = 10) Pageable pageable) {
		List<Cartao> lista = cartaoQuery.consultaPorFiltro(filtro, timeOffset, pageable);
		//Page<Cartao> cartaosPage = lista;
		
		List<CartaoModel> cartaosModel = cartaoModelAssembler
				.toCollectionModel(lista);
		
		Page<CartaoModel> cartaosModelPage = new PageImpl<>(cartaosModel, pageable, 
				lista.size());
		
		return cartaosModelPage;
	}
	
}
