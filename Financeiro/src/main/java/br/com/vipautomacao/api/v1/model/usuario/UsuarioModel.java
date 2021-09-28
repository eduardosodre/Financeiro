package br.com.vipautomacao.api.v1.model.usuario;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class UsuarioModel  {

	@ApiModelProperty(example = "codigo")

	private Integer codigo;

	@ApiModelProperty(example = "nome")

	private String nome;

	@ApiModelProperty(example = "email")

	private String email;

	@ApiModelProperty(example = "senha")

	private String senha;

	@ApiModelProperty(example = "dataNascimento")

	private Date dataNascimento;

	@ApiModelProperty(example = "sexo")

	private String sexo;

	@ApiModelProperty(example = "acesso")

	private Date acesso;
}
