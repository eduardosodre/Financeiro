package br.com.vipautomacao.domain.model;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	
	private Boolean cancelado;
	private OffsetDateTime dataCancel;
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
}
