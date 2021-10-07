package br.com.vipautomacao.domain.filter;
import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter

public class LancamentoFilter {

	@ApiModelProperty(example = "1", value = "codigo para filtro da pesquisa")
	private Integer codigo;

	@ApiModelProperty(example = "Texto", value = "descricao para filtro da pesquisa")
	private String descricao;

	@ApiModelProperty(example = "Texto", value = "valor para filtro da pesquisa")
	private Double valorInicio;

	@ApiModelProperty(example = "Texto", value = "valor para filtro da pesquisa")
	private Double valorFim;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty(example = "2019-10-30T00:00:00Z", value = "data para filtro da pesquisa")
	private Date dataInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty(example = "2019-10-30T00:00:00Z", value = "data para filtro da pesquisa")
	private Date dataFim;

	@ApiModelProperty(example = "1", value = "conta para filtro da pesquisa")
	private Integer conta;

	@ApiModelProperty(example = "1", value = "categoria para filtro da pesquisa")
	private Integer categoria;

	@ApiModelProperty(example = "Texto", value = "tipoLancamento para filtro da pesquisa")
	private String tipoLancamento;

	@ApiModelProperty(example = "1", value = "conta2 para filtro da pesquisa")
	private Integer conta2;

	@ApiModelProperty(example = "Texto", value = "observacao para filtro da pesquisa")
	private String observacao;

	@ApiModelProperty(example = "Texto", value = "parcelaFixa para filtro da pesquisa")
	private Boolean parcelaFixa;

	@ApiModelProperty(example = "1", value = "qtdParcela para filtro da pesquisa")
	private Integer qtdParcela;

	@ApiModelProperty(example = "1", value = "codigoPai para filtro da pesquisa")
	private Integer codigoPai;

	@ApiModelProperty(example = "1", value = "usuario para filtro da pesquisa")
	private Integer usuario;

}
