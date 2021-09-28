package br.com.vipautomacao.domain.filter;
import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UsuarioFilter {

	@ApiModelProperty(example = "1", value = "codigo para filtro da pesquisa")
	private Integer codigo;

	@ApiModelProperty(example = "Texto", value = "nome para filtro da pesquisa")
	private String nome;

	@ApiModelProperty(example = "Texto", value = "email para filtro da pesquisa")
	private String email;

	@ApiModelProperty(example = "Texto", value = "senha para filtro da pesquisa")
	private String senha;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty(example = "2019-10-30T00:00:00Z", value = "dataNascimento para filtro da pesquisa")
	private OffsetDateTime dataNascimentoInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty(example = "2019-10-30T00:00:00Z", value = "dataNascimento para filtro da pesquisa")
	private OffsetDateTime dataNascimentoFim;

	@ApiModelProperty(example = "Texto", value = "sexo para filtro da pesquisa")
	private String sexo;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty(example = "2019-10-30T00:00:00Z", value = "acesso para filtro da pesquisa")
	private OffsetDateTime acessoInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty(example = "2019-10-30T00:00:00Z", value = "acesso para filtro da pesquisa")
	private OffsetDateTime acessoFim;

}
