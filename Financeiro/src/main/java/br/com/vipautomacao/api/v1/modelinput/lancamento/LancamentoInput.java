package br.com.vipautomacao.api.v1.modelinput.lancamento;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaInput;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaIdInput;
import br.com.vipautomacao.api.v1.modelinput.categoria.CategoriaInput;
import br.com.vipautomacao.api.v1.modelinput.categoria.CategoriaIdInput;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaInput;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaIdInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
@Setter
@Getter
public class LancamentoInput {
	@ApiModelProperty(example = "teste", required = true)

	private String descricao;
	@ApiModelProperty(example = "teste", required = true)

	private Double valor;
	@ApiModelProperty(example = "teste", required = true)

	private Date data;
	@ApiModelProperty(example = "teste", required = true)

	private ContaIdInput conta;
	@ApiModelProperty(example = "teste", required = true)

	private CategoriaIdInput categoria;
	@ApiModelProperty(example = "teste", required = true)

	private String tipoLancamento;
	@ApiModelProperty(example = "teste", required = true)

	private ContaIdInput conta2;
	@ApiModelProperty(example = "teste", required = true)

	private String observacao;
	@ApiModelProperty(example = "teste", required = true)

	private Boolean parcelaFixa;
	@ApiModelProperty(example = "teste", required = true)

	private Integer qtdParcela;
	@ApiModelProperty(example = "teste", required = true)

	private Integer codigoPai;
	@ApiModelProperty(example = "teste", required = true)

	private UsuarioIdInput usuario;
}
