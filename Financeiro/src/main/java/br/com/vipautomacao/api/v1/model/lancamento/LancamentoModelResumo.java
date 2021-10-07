package br.com.vipautomacao.api.v1.model.lancamento;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import br.com.vipautomacao.api.v1.model.conta.ContaModel;

import br.com.vipautomacao.api.v1.model.categoria.CategoriaModel;

import br.com.vipautomacao.api.v1.model.conta.ContaModel;

import br.com.vipautomacao.api.v1.model.usuario.UsuarioModel;

@Setter
@Getter
public class LancamentoModelResumo  {

	@ApiModelProperty(example = "codigo")

	private Integer codigo;

	@ApiModelProperty(example = "descricao")

	private String descricao;

	@ApiModelProperty(example = "valor")

	private Double valor;

	@ApiModelProperty(example = "data")

	private Date data;
	private ContaModel conta;
	private CategoriaModel categoria;

	@ApiModelProperty(example = "tipoLancamento")

	private String tipoLancamento;
	private ContaModel conta2;

	@ApiModelProperty(example = "observacao")

	private String observacao;

	@ApiModelProperty(example = "parcelaFixa")

	private Boolean parcelaFixa;

	@ApiModelProperty(example = "qtdParcela")

	private Integer qtdParcela;

	@ApiModelProperty(example = "codigoPai")

	private Integer codigoPai;
	private UsuarioModel usuario;
}
