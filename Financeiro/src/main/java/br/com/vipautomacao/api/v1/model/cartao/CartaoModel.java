package br.com.vipautomacao.api.v1.model.cartao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import br.com.vipautomacao.api.v1.model.instituicaofinanceira.InstituicaoFinanceiraModel;

import br.com.vipautomacao.api.v1.model.conta.ContaModel;

import br.com.vipautomacao.api.v1.model.usuario.UsuarioModel;

@Setter
@Getter
public class CartaoModel  {

	@ApiModelProperty(example = "codigo")

	private Integer codigo;
	private InstituicaoFinanceiraModel instituicao;

	@ApiModelProperty(example = "fechamento")

	private Integer fechamento;

	@ApiModelProperty(example = "vencimento")

	private Integer vencimento;

	@ApiModelProperty(example = "limite")

	private Double limite;
	private ContaModel contaPagamento;
	private UsuarioModel usuario;
}
