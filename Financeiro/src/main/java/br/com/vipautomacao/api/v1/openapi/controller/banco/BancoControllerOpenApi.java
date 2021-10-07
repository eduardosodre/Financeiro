package br.com.vipautomacao.api.v1.openapi.controller.banco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.vipautomacao.api.exceptionhandler.Problem;

import br.com.vipautomacao.api.v1.model.banco.BancoModel;
import br.com.vipautomacao.domain.filter.BancoFilter;
import br.com.vipautomacao.api.v1.modelinput.banco.BancoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Bancos")
public interface BancoControllerOpenApi {
@ApiOperation("Lista as bancos com paginação")
	Page<BancoModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma banco por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da banco inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Banco não encontrada", response = Problem.class)
	})
	BancoModel buscar(
			@ApiParam(value = "ID de uma banco", example = "1", required = true)
			Integer bancoId);
	
	@ApiOperation("Cadastra uma banco")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Banco cadastrada"),
	})
	BancoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova banco", required = true)
			BancoInput bancoInput);
	
	@ApiOperation("Atualiza uma banco por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Banco atualizada"),
		@ApiResponse(code = 404, message = "Banco não encontrada", response = Problem.class)
	})
	BancoModel atualizar(
			@ApiParam(value = "ID de uma banco", example = "1", required = true)
			Integer bancoId,
			
			@ApiParam(name = "corpo", value = "Representação de uma banco com os novos dados", 
				required = true)
			BancoInput bancoInput);
	
	@ApiOperation("Exclui uma banco por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Banco excluída"),
		@ApiResponse(code = 404, message = "Banco não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma banco", example = "1", required = true)
			Integer bancoId);


	@ApiOperation("Busca uma banco por Filtro")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Filtro da banco inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Banco não encontrada", response = Problem.class)
	})
	Page<BancoModel> consultaPorFiltro(
			@ApiParam(value = "ID de uma banco", example = "1", required = true)
			BancoFilter filtro, 
	 String timeOffset, 
	Pageable pageable);

}
