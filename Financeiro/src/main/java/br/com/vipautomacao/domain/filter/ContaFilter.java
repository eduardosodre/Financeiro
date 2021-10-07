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

public class ContaFilter {

	@ApiModelProperty(example = "1", value = "codigo para filtro da pesquisa")
	private Integer codigo;

	@ApiModelProperty(example = "1", value = "banco para filtro da pesquisa")
	private Integer banco;

	@ApiModelProperty(example = "Texto", value = "nome para filtro da pesquisa")
	private String nome;

	@ApiModelProperty(example = "Texto", value = "saldoInicial para filtro da pesquisa")
	private Double saldoInicialInicio;

	@ApiModelProperty(example = "Texto", value = "saldoInicial para filtro da pesquisa")
	private Double saldoInicialFim;

	@ApiModelProperty(example = "Texto", value = "descricao para filtro da pesquisa")
	private String descricao;

	@ApiModelProperty(example = "1", value = "usuario para filtro da pesquisa")
	private Integer usuario;

}
