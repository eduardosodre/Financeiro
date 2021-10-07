package br.com.vipautomacao.api.v1.openapi.controller.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.vipautomacao.api.exceptionhandler.Problem;

import br.com.vipautomacao.api.v1.model.categoria.CategoriaModel;
import br.com.vipautomacao.domain.filter.CategoriaFilter;
import br.com.vipautomacao.api.v1.modelinput.categoria.CategoriaInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Categorias")
public interface CategoriaControllerOpenApi {
@ApiOperation("Lista as categorias com paginação")
	Page<CategoriaModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma categoria por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da categoria inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Categoria não encontrada", response = Problem.class)
	})
	CategoriaModel buscar(
			@ApiParam(value = "ID de uma categoria", example = "1", required = true)
			Integer categoriaId);
	
	@ApiOperation("Cadastra uma categoria")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Categoria cadastrada"),
	})
	CategoriaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova categoria", required = true)
			CategoriaInput categoriaInput);
	
	@ApiOperation("Atualiza uma categoria por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Categoria atualizada"),
		@ApiResponse(code = 404, message = "Categoria não encontrada", response = Problem.class)
	})
	CategoriaModel atualizar(
			@ApiParam(value = "ID de uma categoria", example = "1", required = true)
			Integer categoriaId,
			
			@ApiParam(name = "corpo", value = "Representação de uma categoria com os novos dados", 
				required = true)
			CategoriaInput categoriaInput);
	
	@ApiOperation("Exclui uma categoria por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Categoria excluída"),
		@ApiResponse(code = 404, message = "Categoria não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma categoria", example = "1", required = true)
			Integer categoriaId);


	@ApiOperation("Busca uma categoria por Filtro")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Filtro da categoria inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Categoria não encontrada", response = Problem.class)
	})
	Page<CategoriaModel> consultaPorFiltro(
			@ApiParam(value = "ID de uma categoria", example = "1", required = true)
			CategoriaFilter filtro, 
	 String timeOffset, 
	Pageable pageable);

}
