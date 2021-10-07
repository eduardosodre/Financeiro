package br.com.vipautomacao.api.v1.modelinput.instituicaofinanceira;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Setter
@Getter
public class InstituicaoFinanceiraInput {
	@ApiModelProperty(example = "teste", required = true)

	private String nome;
	@ApiModelProperty(example = "teste", required = true)

	private String imagem;
}
