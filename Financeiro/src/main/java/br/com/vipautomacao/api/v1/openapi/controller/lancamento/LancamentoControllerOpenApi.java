package br.com.vipautomacao.api.v1.openapi.controller.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.vipautomacao.api.exceptionhandler.Problem;

import br.com.vipautomacao.api.v1.model.lancamento.LancamentoModel;
import br.com.vipautomacao.domain.filter.LancamentoFilter;
import br.com.vipautomacao.api.v1.modelinput.lancamento.LancamentoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Lancamentos")
public interface LancamentoControllerOpenApi {
@ApiOperation("Lista as lancamentos com paginação")
	Page<LancamentoModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma lancamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da lancamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Lancamento não encontrada", response = Problem.class)
	})
	LancamentoModel buscar(
			@ApiParam(value = "ID de uma lancamento", example = "1", required = true)
			Integer lancamentoId);
	
	@ApiOperation("Cadastra uma lancamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Lancamento cadastrada"),
	})
	LancamentoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova lancamento", required = true)
			LancamentoInput lancamentoInput);
	
	@ApiOperation("Atualiza uma lancamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Lancamento atualizada"),
		@ApiResponse(code = 404, message = "Lancamento não encontrada", response = Problem.class)
	})
	LancamentoModel atualizar(
			@ApiParam(value = "ID de uma lancamento", example = "1", required = true)
			Integer lancamentoId,
			
			@ApiParam(name = "corpo", value = "Representação de uma lancamento com os novos dados", 
				required = true)
			LancamentoInput lancamentoInput);
	
	@ApiOperation("Exclui uma lancamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Lancamento excluída"),
		@ApiResponse(code = 404, message = "Lancamento não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma lancamento", example = "1", required = true)
			Integer lancamentoId);


	@ApiOperation("Busca uma lancamento por Filtro")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Filtro da lancamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Lancamento não encontrada", response = Problem.class)
	})
	Page<LancamentoModel> consultaPorFiltro(
			@ApiParam(value = "ID de uma lancamento", example = "1", required = true)
			LancamentoFilter filtro, 
	 String timeOffset, 
	Pageable pageable);

}
