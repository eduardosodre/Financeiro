package br.com.vipautomacao.api.v1.openapi.controller.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.vipautomacao.api.exceptionhandler.Problem;
import br.com.vipautomacao.api.v1.model.usuario.SenhaInput;
import br.com.vipautomacao.api.v1.model.usuario.UsuarioModel;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {
@ApiOperation("Lista as usuarios com paginação")
	Page<UsuarioModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma usuario por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da usuario inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuario não encontrada", response = Problem.class)
	})
	UsuarioModel buscar(
			@ApiParam(value = "ID de uma usuario", example = "1", required = true)
			Integer usuarioId);
	
	@ApiOperation("Cadastra uma usuario")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuario cadastrada"),
	})
	UsuarioModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova usuario", required = true)
			UsuarioInput usuarioInput);
	
	@ApiOperation("Atualiza uma usuario por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuario atualizada"),
		@ApiResponse(code = 404, message = "Usuario não encontrada", response = Problem.class)
	})
	UsuarioModel atualizar(
			@ApiParam(value = "ID de uma usuario", example = "1", required = true)
			Integer usuarioId,
			
			@ApiParam(name = "corpo", value = "Representação de uma usuario com os novos dados", 
				required = true)
			UsuarioInput usuarioInput);
	
//	@ApiOperation("Exclui uma usuario por ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Usuario excluída"),
//		@ApiResponse(code = 404, message = "Usuario não encontrada", response = Problem.class)
//	})
//	void remover(
//			@ApiParam(value = "ID de uma usuario", example = "1", required = true)
//			Integer usuarioId);
//
//
//	@ApiOperation("Busca uma usuario por Filtro")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "Filtro da usuario inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Usuario não encontrada", response = Problem.class)
//	})
//	Page<UsuarioModel> consultaPorFiltro(
//			@ApiParam(value = "ID de uma usuario", example = "1", required = true)
//			UsuarioFilter filtro, 
//	 String timeOffset, 
//	Pageable pageable);
	
	@ApiOperation("Atualiza a senha de um usuário")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Senha alterada com sucesso"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	void alterarSenha(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Integer usuarioId,
			
			@ApiParam(name = "corpo", value = "Representação de uma nova senha", 
				required = true)
			SenhaInput senha);

}
