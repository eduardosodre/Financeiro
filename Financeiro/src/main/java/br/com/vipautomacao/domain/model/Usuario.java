package br.com.vipautomacao.domain.model;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODIGO"  )
	private Integer codigo;
	@Column(name="nome"  )
	private String nome;
	@Column(name="email"  )
	private String email;
	@Column(name="senha"  )
	private String senha;
	@Column(name="dataNascimento"  )
	private OffsetDateTime dataNascimento;
	@Column(name="sexo"  )
	private String sexo;
	@Column(name="acesso"  )
	private Date acesso;
}
