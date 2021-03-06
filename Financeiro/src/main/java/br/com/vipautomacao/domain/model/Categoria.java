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
@Table(name = "categoria")
public class Categoria {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODIGO"  )
	private Integer codigo;
	@Column(name="descricao"  )
	private String descricao;
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	@Column(name="tipo"  )
	private String tipo;
	
	private Boolean cancelado;
	private OffsetDateTime dataCancel;
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
}
