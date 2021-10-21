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

import br.com.vipautomacao.api.v1.assembler.lancamento.LancamentoInputDisassembler;
import br.com.vipautomacao.api.v1.assembler.lancamento.LancamentoModelAssembler;
import br.com.vipautomacao.api.v1.model.lancamento.LancamentoModel;
import br.com.vipautomacao.api.v1.modelinput.lancamento.LancamentoInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
import br.com.vipautomacao.api.v1.openapi.controller.lancamento.LancamentoControllerOpenApi;
import br.com.vipautomacao.core.security.CheckSecurity;
import br.com.vipautomacao.core.security.VipSecurity;
import br.com.vipautomacao.domain.exception.ContaNaoEncontradoException;
import br.com.vipautomacao.domain.filter.LancamentoFilter;
import br.com.vipautomacao.domain.model.EPermissao;
import br.com.vipautomacao.domain.model.Lancamento;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.service.LancamentoService;
import br.com.vipautomacao.domain.service.query.LancamentoQueryService;

@RestController
@RequestMapping(path = "/v1/lancamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class LancamentoController implements LancamentoControllerOpenApi {

	@Autowired
	private VipSecurity vipSecurity;

	@Autowired
	private LancamentoService cadastroLancamento;

	@Autowired
	private LancamentoModelAssembler lancamentoModelAssembler;

	@Autowired
	private LancamentoInputDisassembler lancamentoInputDisassembler;

	@Autowired
	private LancamentoQueryService lancamentoQuery;

	@CheckSecurity.Lancamento.PodeConsultar
	@Override
	@GetMapping
	public Page<LancamentoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Integer usuarioId = vipSecurity.getUsuarioId();
		if (vipSecurity.hasAuthority(EPermissao.GERENCIAR_CONTA.getDescricao())) {
			usuarioId = null;
		}
		List<Lancamento> lista = lancamentoQuery.consultaPorUsuario(usuarioId, pageable);

		List<LancamentoModel> lancamentosModel = lancamentoModelAssembler.toCollectionModel(lista);

		Page<LancamentoModel> lancamentosModelPage = new PageImpl<>(lancamentosModel, pageable, lista.size());

		return lancamentosModelPage;
	}

	@CheckSecurity.Lancamento.PodeBuscar
	@Override
	@GetMapping("/{lancamentoId}")
	public LancamentoModel buscar(@PathVariable Integer lancamentoId) {
		Lancamento lancamento = cadastroLancamento.buscarOuFalhar(lancamentoId);

		return lancamentoModelAssembler.toModel(lancamento);
	}

	@CheckSecurity.Lancamento.PodeCriar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LancamentoModel adicionar(@RequestBody @Valid LancamentoInput lancamentoInput) {
		Lancamento lancamento = lancamentoInputDisassembler.toDomainObject(lancamentoInput);
		lancamento.setUsuario(new Usuario());
		lancamento.getUsuario().setCodigo(vipSecurity.getUsuarioId());
		lancamento.setCancelado(false);
		lancamento = cadastroLancamento.salvar(lancamento);

		return lancamentoModelAssembler.toModel(lancamento);
	}

	@CheckSecurity.Lancamento.PodeEditar
	@Override
	@PutMapping("/{lancamentoId}")
	public LancamentoModel atualizar(@PathVariable Integer lancamentoId,
			@RequestBody @Valid LancamentoInput lancamentoInput) {
		Lancamento lancamentoAtual = cadastroLancamento.buscarOuFalhar(lancamentoId);

		if (lancamentoAtual.getCancelado()) {
			throw new ContaNaoEncontradoException(lancamentoId);
		}

		lancamentoInput.setUsuario(new UsuarioIdInput());
		lancamentoInput.getUsuario().setCodigo(vipSecurity.getUsuarioId());

		Lancamento antigo = br.com.vipautomacao.infrastructure.util.ReflectionUtil.clone(lancamentoAtual);

		lancamentoInputDisassembler.copyToDomainObject(lancamentoInput, lancamentoAtual);
		lancamentoAtual.setCodigo(lancamentoId);

		lancamentoAtual = cadastroLancamento.atualizarLancamento(antigo, lancamentoAtual);

		return lancamentoModelAssembler.toModel(lancamentoAtual);
	}

	@CheckSecurity.Lancamento.PodeEditar
	@Override
	@DeleteMapping("/{lancamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer lancamentoId) {
		// cadastroLancamento.excluir(lancamentoId);

		Lancamento lancamentoAtual = cadastroLancamento.buscarOuFalhar(lancamentoId);

		if (lancamentoAtual.getCancelado()) {
			throw new ContaNaoEncontradoException(lancamentoId);
		}

		lancamentoAtual.setCancelado(true);
		lancamentoAtual.setDataCancel(OffsetDateTime.now());
		cadastroLancamento.salvar(lancamentoAtual);
	}

	@CheckSecurity.Lancamento.PodeConsultar
	@Override
	@GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<LancamentoModel> consultaPorFiltro(LancamentoFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset,
			@PageableDefault(size = 10) Pageable pageable) {

		Integer usuarioId = vipSecurity.getUsuarioId();
		if (vipSecurity.hasAuthority(EPermissao.GERENCIAR_LANCAMENTO.getDescricao())) {
			usuarioId = null;
		}
		if(usuarioId != null) {
			filtro.setUsuario(usuarioId);
		}
		List<Lancamento> lista = lancamentoQuery.consultaPorFiltro(filtro, timeOffset, pageable);

		List<LancamentoModel> lancamentosModel = lancamentoModelAssembler.toCollectionModel(lista);

		Page<LancamentoModel> lancamentosModelPage = new PageImpl<>(lancamentosModel, pageable, lista.size());

		return lancamentosModelPage;
	}

}
