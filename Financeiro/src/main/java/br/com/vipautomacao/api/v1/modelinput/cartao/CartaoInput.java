package br.com.vipautomacao.api.v1.modelinput.cartao;

import javax.validation.constraints.NotBlank;

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
public class CartaoInput {
	@ApiModelProperty(example = "teste", required = true)

	private InstituicaoFinanceiraIdInput instituicao;
	@ApiModelProperty(example = "teste", required = true)

	private Integer fechamento;
	@ApiModelProperty(example = "teste", required = true)

	private Integer vencimento;
	@ApiModelProperty(example = "teste", required = true)

	private Double limite;
	@ApiModelProperty(example = "teste", required = true)

	private ContaIdInput contaPagamento;
	@ApiModelProperty(example = "teste", required = true)

	private UsuarioIdInput usuario;
}
