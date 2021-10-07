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
@Table(name = "conta")
public class Conta {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODIGO"  )
	private Integer codigo;
	@ManyToOne
	@JoinColumn(name="banco")
	private Banco banco;
	@Column(name="nome"  )
	private String nome;
	@Column(name="saldoInicial"  )
	private Double saldoInicial;
	@Column(name="descricao"  )
	private String descricao;
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
}
