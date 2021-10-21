package br.com.vipautomacao.api.v1.modelinput.conta;

import br.com.vipautomacao.api.v1.modelinput.banco.BancoIdInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ContaInput {
	@ApiModelProperty(example = "teste", required = true)
	private BancoIdInput banco;
	
	@ApiModelProperty(example = "teste", required = true)
	private String nome;
	
	@ApiModelProperty(example = "teste", required = true)
	private Double saldoInicial;
	
	@ApiModelProperty(example = "teste", required = true)
	private String descricao;
	
	@ApiModelProperty(example = "teste", required = true)
	private UsuarioIdInput usuario;
}
