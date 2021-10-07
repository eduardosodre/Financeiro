package br.com.vipautomacao.api.v1.modelinput.categoria;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
@Setter
@Getter
public class CategoriaInput {
	@ApiModelProperty(example = "teste", required = true)

	private String descricao;
	@ApiModelProperty(example = "teste", required = true)

	private UsuarioIdInput usuario;
	@ApiModelProperty(example = "teste", required = true)

	private String tipo;
}
