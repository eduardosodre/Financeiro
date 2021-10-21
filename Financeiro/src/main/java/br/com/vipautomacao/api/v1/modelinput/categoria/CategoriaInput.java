package br.com.vipautomacao.api.v1.modelinput.categoria;

import javax.validation.constraints.NotBlank;

import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CategoriaInput {
	@NotBlank
	@ApiModelProperty(example = "teste", required = true)
	private String descricao;
	
	@NotBlank
	@ApiModelProperty(example = "teste", required = true)
	private String tipo;
	
	@ApiModelProperty(example = "teste", required = true)
	private UsuarioIdInput usuario;
}
