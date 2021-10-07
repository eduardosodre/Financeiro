package br.com.vipautomacao.api.v1.openapi.controller.cartao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.vipautomacao.api.exceptionhandler.Problem;

import br.com.vipautomacao.api.v1.model.cartao.CartaoModel;
import br.com.vipautomacao.domain.filter.CartaoFilter;
import br.com.vipautomacao.api.v1.modelinput.cartao.CartaoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Cartaos")
public interface CartaoControllerOpenApi {
@ApiOperation("Lista as cartaos com paginação")
	Page<CartaoModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma cartao por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cartao inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cartao não encontrada", response = Problem.class)
	})
	CartaoModel buscar(
			@ApiParam(value = "ID de uma cartao", example = "1", required = true)
			Integer cartaoId);
	
	@ApiOperation("Cadastra uma cartao")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cartao cadastrada"),
	})
	CartaoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cartao", required = true)
			CartaoInput cartaoInput);
	
	@ApiOperation("Atualiza uma cartao por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cartao atualizada"),
		@ApiResponse(code = 404, message = "Cartao não encontrada", response = Problem.class)
	})
	CartaoModel atualizar(
			@ApiParam(value = "ID de uma cartao", example = "1", required = true)
			Integer cartaoId,
			
			@ApiParam(name = "corpo", value = "Representação de uma cartao com os novos dados", 
				required = true)
			CartaoInput cartaoInput);
	
	@ApiOperation("Exclui uma cartao por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cartao excluída"),
		@ApiResponse(code = 404, message = "Cartao não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma cartao", example = "1", required = true)
			Integer cartaoId);


	@ApiOperation("Busca uma cartao por Filtro")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Filtro da cartao inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cartao não encontrada", response = Problem.class)
	})
	Page<CartaoModel> consultaPorFiltro(
			@ApiParam(value = "ID de uma cartao", example = "1", required = true)
			CartaoFilter filtro, 
	 String timeOffset, 
	Pageable pageable);

}
