package br.com.vipautomacao.api.v1.modelinput.instituicaofinanceira;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Setter
@Getter
public class InstituicaoFinanceiraIdInput {
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Integer codigo;
}
