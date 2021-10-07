package br.com.vipautomacao.core.security;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Usuario {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("@vipSecurity.podeConsultarUsuario()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}

	public @interface Banco {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GERENCIAR_BANCOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciar { }
		
	}

	public @interface InstituicaoFinanceira {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GERENCIAR_INSTITUICOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciar { }
		
	}

	public @interface Conta {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CONTA')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("@vipSecurity.podeConsultarConta()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}

	public @interface Cartao {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CARTAO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("@vipSecurity.podeConsultarCartao()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}

	public @interface Lancamento {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_LANCAMENTO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("@vipSecurity.podeConsultarLancamento()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}

	public @interface Categoria {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CATEGORIA')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("@vipSecurity.podeConsultarCategoria()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}

}