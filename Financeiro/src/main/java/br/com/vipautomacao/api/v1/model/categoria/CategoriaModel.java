package br.com.vipautomacao.api.v1.model.categoria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import br.com.vipautomacao.api.v1.model.usuario.UsuarioModel;

@Setter
@Getter
public class CategoriaModel  {

	@ApiModelProperty(example = "codigo")

	private Integer codigo;

	@ApiModelProperty(example = "descricao")

	private String descricao;
	private UsuarioModel usuario;

	@ApiModelProperty(example = "tipo")

	private String tipo;
}
