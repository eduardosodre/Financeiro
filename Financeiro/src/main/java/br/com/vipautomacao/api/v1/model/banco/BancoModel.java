package br.com.vipautomacao.api.v1.model.banco;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class BancoModel  {

	@ApiModelProperty(example = "codigo")

	private Integer codigo;

	@ApiModelProperty(example = "nome")

	private String nome;

	@ApiModelProperty(example = "imagem")

	private String imagem;
}
