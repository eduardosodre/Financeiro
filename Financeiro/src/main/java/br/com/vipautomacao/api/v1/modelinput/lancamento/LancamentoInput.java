package br.com.vipautomacao.api.v1.modelinput.lancamento;

import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import br.com.vipautomacao.api.v1.modelinput.categoria.CategoriaIdInput;
import br.com.vipautomacao.api.v1.modelinput.conta.ContaIdInput;
import br.com.vipautomacao.api.v1.modelinput.usuario.UsuarioIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LancamentoInput {
	@ApiModelProperty(example = "Descrição", required = true)
	@NotBlank
	private String descricao;

	@Positive
	@NotNull
	@ApiModelProperty(example = "10.90", required = true)
	private Double valor;

	@NotNull
	@ApiModelProperty(example = "2021-01-01T01:50:55Z", required = true)
	private OffsetDateTime data;

	@Valid
	@NotNull
	@ApiModelProperty(example = "1", required = true)
	private ContaIdInput conta;

	@Valid
	@NotNull
	@ApiModelProperty(example = "teste", required = true)
	private CategoriaIdInput categoria;

	@NotNull
	@ApiModelProperty(example = "teste", required = true)
	private String tipoLancamento;

	@ApiModelProperty(example = "teste", required = true)
	private ContaIdInput conta2;

	@ApiModelProperty(example = "teste", required = true)
	private String observacao;

	@NotNull
	@ApiModelProperty(example = "teste", required = true)
	private Boolean parcelaFixa;

	@PositiveOrZero
	@NotNull
	@ApiModelProperty(example = "teste", required = true)
	private Integer qtdParcela;

	@ApiModelProperty(example = "teste", required = true)
	private Integer codigoPai;
	
	@NotNull
	@ApiModelProperty(example = "Pago", required = true)
	private Boolean pago;

	@ApiModelProperty(example = "teste", required = true)
	private UsuarioIdInput usuario;
}
