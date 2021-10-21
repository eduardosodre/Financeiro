package br.com.vipautomacao.api.v1.model.usuario;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModelResumo  {

	@ApiModelProperty(example = "codigo")
	private Integer codigo;
	
	@ApiModelProperty(example = "nome")
	private String nome;


}
