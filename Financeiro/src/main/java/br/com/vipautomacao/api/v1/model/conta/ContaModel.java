package br.com.vipautomacao.api.v1.model.conta;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import br.com.vipautomacao.api.v1.model.banco.BancoModel;

import br.com.vipautomacao.api.v1.model.usuario.UsuarioModel;

@Setter
@Getter
public class ContaModel  {

	@ApiModelProperty(example = "codigo")

	private Integer codigo;
	private BancoModel banco;

	@ApiModelProperty(example = "nome")

	private String nome;

	@ApiModelProperty(example = "saldoInicial")

	private Double saldoInicial;

	@ApiModelProperty(example = "descricao")

	private String descricao;
	private UsuarioModel usuario;
}
