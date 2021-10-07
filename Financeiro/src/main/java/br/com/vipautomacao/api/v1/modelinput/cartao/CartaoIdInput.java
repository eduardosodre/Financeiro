package br.com.vipautomacao.api.v1.modelinput.cartao;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import br.com.vipautomacao.api.v1.modelinput.instituicaofinanceira.InstituicaoFinanceiraInput;
import br.com.vipautomacao.api.v1.modelinput.instituicaofinanceira.InstituicaoFinanceiraIdInput;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaInput;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaIdInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
@Setter
@Getter
public class CartaoIdInput {
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Integer codigo;
}
