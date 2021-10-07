package br.com.vipautomacao.api.v1.openapi.controller.conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.vipautomacao.api.exceptionhandler.Problem;

import br.com.vipautomacao.api.v1.model.conta.ContaModel;
import br.com.vipautomacao.domain.filter.ContaFilter;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Contas")
public interface ContaControllerOpenApi {
@ApiOperation("Lista as contas com paginação")
	Page<ContaModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma conta por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da conta inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Conta não encontrada", response = Problem.class)
	})
	ContaModel buscar(
			@ApiParam(value = "ID de uma conta", example = "1", required = true)
			Integer contaId);
	
	@ApiOperation("Cadastra uma conta")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Conta cadastrada"),
	})
	ContaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova conta", required = true)
			ContaInput contaInput);
	
	@ApiOperation("Atualiza uma conta por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Conta atualizada"),
		@ApiResponse(code = 404, message = "Conta não encontrada", response = Problem.class)
	})
	ContaModel atualizar(
			@ApiParam(value = "ID de uma conta", example = "1", required = true)
			Integer contaId,
			
			@ApiParam(name = "corpo", value = "Representação de uma conta com os novos dados", 
				required = true)
			ContaInput contaInput);
	
	@ApiOperation("Exclui uma conta por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Conta excluída"),
		@ApiResponse(code = 404, message = "Conta não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma conta", example = "1", required = true)
			Integer contaId);


	@ApiOperation("Busca uma conta por Filtro")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Filtro da conta inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Conta não encontrada", response = Problem.class)
	})
	Page<ContaModel> consultaPorFiltro(
			@ApiParam(value = "ID de uma conta", example = "1", required = true)
			ContaFilter filtro, 
	 String timeOffset, 
	Pageable pageable);

}
