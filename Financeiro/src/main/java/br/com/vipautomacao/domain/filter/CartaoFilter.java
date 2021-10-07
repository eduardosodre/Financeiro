package br.com.vipautomacao.domain.filter;
import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter

public class CartaoFilter {

	@ApiModelProperty(example = "1", value = "codigo para filtro da pesquisa")
	private Integer codigo;

	@ApiModelProperty(example = "1", value = "instituicao para filtro da pesquisa")
	private Integer instituicao;

	@ApiModelProperty(example = "1", value = "fechamento para filtro da pesquisa")
	private Integer fechamento;

	@ApiModelProperty(example = "1", value = "vencimento para filtro da pesquisa")
	private Integer vencimento;

	@ApiModelProperty(example = "Texto", value = "limite para filtro da pesquisa")
	private Double limiteInicio;

	@ApiModelProperty(example = "Texto", value = "limite para filtro da pesquisa")
	private Double limiteFim;

	@ApiModelProperty(example = "1", value = "contaPagamento para filtro da pesquisa")
	private Integer contaPagamento;

	@ApiModelProperty(example = "1", value = "usuario para filtro da pesquisa")
	private Integer usuario;

}
