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
	private Double saldoFinal;
	@Column(name="descricao"  )
	private String descricao;
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	private Boolean cancelado;
	private OffsetDateTime dataCancel;
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	public void ajustarSaldo(Lancamento l) {
		ajustarSaldo(l, l.getValor());
	}
	
	public void ajustarSaldo(Lancamento l, Double valor) {
		if(l.getCancelado()) {
			if(l.getTipoLancamento().equalsIgnoreCase("E")) {
				this.saldoFinal -= valor;
			}else if(l.getTipoLancamento().equalsIgnoreCase("S")) {
				this.saldoFinal += valor;
			}else if(l.getTipoLancamento().equalsIgnoreCase("T")) {
				if(l.getConta().getCodigo().equals(codigo)) {
					this.saldoFinal += valor;
				}else if(l.getConta2().getCodigo().equals(codigo)) {
					this.saldoFinal -= valor;
				}
			}
		}else {
			if(l.getTipoLancamento().equalsIgnoreCase("E")) {
				this.saldoFinal += valor;
			}else if(l.getTipoLancamento().equalsIgnoreCase("S")) {
				this.saldoFinal -= valor;
			}else if(l.getTipoLancamento().equalsIgnoreCase("T")) {
				if(l.getConta().getCodigo().equals(codigo)) {
					this.saldoFinal -= valor;
				}else if(l.getConta2().getCodigo().equals(codigo)) {
					this.saldoFinal += valor;
				}
			}
		}
	}
	
}
