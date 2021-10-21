package br.com.vipautomacao.api.v1.model.lancamento;

import java.time.OffsetDateTime;

import br.com.vipautomacao.api.v1.model.categoria.CategoriaModelResumo;
import br.com.vipautomacao.api.v1.model.conta.ContaModelResumo;
import br.com.vipautomacao.api.v1.model.usuario.UsuarioModelResumo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LancamentoModel {

	@ApiModelProperty(example = "codigo")
	private Integer codigo;

	@ApiModelProperty(example = "descricao")
	private String descricao;

	@ApiModelProperty(example = "valor")
	private Double valor;

	@ApiModelProperty(example = "data")
	private OffsetDateTime data;
	private ContaModelResumo conta;
	private CategoriaModelResumo categoria;

	@ApiModelProperty(example = "tipoLancamento")
	private String tipoLancamento;
	private ContaModelResumo conta2;

	@ApiModelProperty(example = "observacao")
	private String observacao;

	@ApiModelProperty(example = "parcelaFixa")
	private Boolean parcelaFixa;

	@ApiModelProperty(example = "qtdParcela")
	private Integer qtdParcela;

	@ApiModelProperty(example = "codigoPai")
	private Integer codigoPai;
	
	@ApiModelProperty(example = "pago")
	private Boolean pago;
	
	private UsuarioModelResumo usuario;
	
	
}
