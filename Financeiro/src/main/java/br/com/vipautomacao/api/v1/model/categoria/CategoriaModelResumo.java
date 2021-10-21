package br.com.vipautomacao.api.v1.model.categoria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoriaModelResumo  {

	@ApiModelProperty(example = "codigo")
	private Integer codigo;

	@ApiModelProperty(example = "descricao")
	private String descricao;

	@ApiModelProperty(example = "tipo")
	private String tipo;
}
