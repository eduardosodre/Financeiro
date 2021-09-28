package br.com.vipautomacao.api.v1.modelinput.usuario;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Setter
@Getter
public class UsuarioInput {
	@ApiModelProperty(example = "teste", required = true)

	@NotBlank

	private String nome;
	@ApiModelProperty(example = "teste", required = true)

	@NotBlank

	private String email;
	@ApiModelProperty(example = "teste", required = true)

	@NotBlank

	private String senha;
	@ApiModelProperty(example = "teste", required = true)

	private Date dataNascimento;
	@ApiModelProperty(example = "teste", required = true)

	private String sexo;
	@ApiModelProperty(example = "teste", required = true)

	private Date acesso;
}
