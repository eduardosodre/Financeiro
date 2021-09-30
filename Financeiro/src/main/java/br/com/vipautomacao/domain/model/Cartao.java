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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cartao")
public class Cartao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODIGO"  )
	private Integer codigo;
	@ManyToOne
	@JoinColumn(name="instituicao")
	private InstituicaoFinanceira instituicao;
	@Column(name="fechamento"  )
	private Integer fechamento;
	@Column(name="vencimento"  )
	private Integer vencimento;
	@Column(name="limite"  )
	private Double limite;
	@ManyToOne
	@JoinColumn(name="contaPagamento")
	private Conta contaPagamento;
}
