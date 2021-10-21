package br.com.vipautomacao.domain.filter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ContaFilter {

	@ApiModelProperty(example = "1", value = "banco para filtro da pesquisa")
	private Integer banco;

	@ApiModelProperty(example = "Texto", value = "nome para filtro da pesquisa")
	private String nome;

	@ApiModelProperty(example = "1", value = "usuario para filtro da pesquisa")
	private Integer usuario;

}
