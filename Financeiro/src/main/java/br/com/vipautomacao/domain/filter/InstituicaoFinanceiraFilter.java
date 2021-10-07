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

public class InstituicaoFinanceiraFilter {

	@ApiModelProperty(example = "1", value = "codigo para filtro da pesquisa")
	private Integer codigo;

	@ApiModelProperty(example = "Texto", value = "nome para filtro da pesquisa")
	private String nome;

	@ApiModelProperty(example = "Texto", value = "imagem para filtro da pesquisa")
	private String imagem;

}
