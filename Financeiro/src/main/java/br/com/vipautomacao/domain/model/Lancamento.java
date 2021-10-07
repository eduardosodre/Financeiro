package br.com.vipautomacao.domain.model;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "lancamento")
public class Lancamento {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODIGO"  )
	private Integer codigo;
	@Column(name="descricao"  )
	private String descricao;
	@Column(name="valor"  )
	private Double valor;
	@Column(name="data"  )
	private Date data;
	@ManyToOne
	@JoinColumn(name="conta")
	private Conta conta;
	@ManyToOne
	@JoinColumn(name="categoria")
	private Categoria categoria;
	@Column(name="tipoLancamento"  )
	private String tipoLancamento;
	@ManyToOne
	@JoinColumn(name="conta2")
	private Conta conta2;
	@Column(name="observacao"  )
	private String observacao;
	@Column(name="parcelaFixa"  )
	private Boolean parcelaFixa;
	@Column(name="qtdParcela"  )
	private Integer qtdParcela;
	@Column(name="codigoPai"  )
	private Integer codigoPai;
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
}
