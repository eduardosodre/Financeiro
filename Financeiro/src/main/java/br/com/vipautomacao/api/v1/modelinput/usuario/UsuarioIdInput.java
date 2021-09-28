package br.com.vipautomacao.api.v1.modelinput.usuario;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UsuarioIdInput {
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Integer codigo;
}
