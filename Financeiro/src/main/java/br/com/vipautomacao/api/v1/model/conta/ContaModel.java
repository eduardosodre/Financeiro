package br.com.vipautomacao.api.v1.model.conta;

import br.com.vipautomacao.api.v1.model.banco.BancoModel;
import br.com.vipautomacao.api.v1.model.usuario.UsuarioModelResumo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaModel {

	@ApiModelProperty(example = "codigo")
	private Integer codigo;

	private BancoModel banco;
	
	private UsuarioModelResumo usuario;

	@ApiModelProperty(example = "nome")
	private String nome;

	@ApiModelProperty(example = "saldoInicial")
	private Double saldoInicial;
	
	@ApiModelProperty(example = "saldoFinal")
	private Double saldoFinal;

	@ApiModelProperty(example = "descricao")
	private String descricao;
}
