package br.com.vipautomacao.api.v1.openapi.controller.instituicaofinanceira;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.vipautomacao.api.exceptionhandler.Problem;

import br.com.vipautomacao.api.v1.model.instituicaofinanceira.InstituicaoFinanceiraModel;
import br.com.vipautomacao.domain.filter.InstituicaoFinanceiraFilter;
import br.com.vipautomacao.api.v1.modelinput.instituicaofinanceira.InstituicaoFinanceiraInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "InstituicaoFinanceiras")
public interface InstituicaoFinanceiraControllerOpenApi {
@ApiOperation("Lista as instituicaoFinanceiras com paginação")
	Page<InstituicaoFinanceiraModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma instituicaoFinanceira por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da instituicaoFinanceira inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "InstituicaoFinanceira não encontrada", response = Problem.class)
	})
	InstituicaoFinanceiraModel buscar(
			@ApiParam(value = "ID de uma instituicaoFinanceira", example = "1", required = true)
			Integer instituicaoFinanceiraId);
	
	@ApiOperation("Cadastra uma instituicaoFinanceira")
	@ApiResponses({
		@ApiResponse(code = 201, message = "InstituicaoFinanceira cadastrada"),
	})
	InstituicaoFinanceiraModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova instituicaoFinanceira", required = true)
			InstituicaoFinanceiraInput instituicaoFinanceiraInput);
	
	@ApiOperation("Atualiza uma instituicaoFinanceira por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "InstituicaoFinanceira atualizada"),
		@ApiResponse(code = 404, message = "InstituicaoFinanceira não encontrada", response = Problem.class)
	})
	InstituicaoFinanceiraModel atualizar(
			@ApiParam(value = "ID de uma instituicaoFinanceira", example = "1", required = true)
			Integer instituicaoFinanceiraId,
			
			@ApiParam(name = "corpo", value = "Representação de uma instituicaoFinanceira com os novos dados", 
				required = true)
			InstituicaoFinanceiraInput instituicaoFinanceiraInput);
	
	@ApiOperation("Exclui uma instituicaoFinanceira por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "InstituicaoFinanceira excluída"),
		@ApiResponse(code = 404, message = "InstituicaoFinanceira não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma instituicaoFinanceira", example = "1", required = true)
			Integer instituicaoFinanceiraId);


	@ApiOperation("Busca uma instituicaoFinanceira por Filtro")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Filtro da instituicaoFinanceira inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "InstituicaoFinanceira não encontrada", response = Problem.class)
	})
	Page<InstituicaoFinanceiraModel> consultaPorFiltro(
			@ApiParam(value = "ID de uma instituicaoFinanceira", example = "1", required = true)
			InstituicaoFinanceiraFilter filtro, 
	 String timeOffset, 
	Pageable pageable);

}
