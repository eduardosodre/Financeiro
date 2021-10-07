package br.com.vipautomacao.api.v1.modelinput.categoria;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
@Setter
@Getter
public class CategoriaIdInput {
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Integer codigo;
}
