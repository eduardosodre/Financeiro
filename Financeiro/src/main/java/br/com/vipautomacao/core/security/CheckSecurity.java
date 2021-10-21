package br.com.vipautomacao.core.security;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import br.com.vipautomacao.domain.model.EPermissao;

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
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
	}

	public @interface InstituicaoFinanceira {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GERENCIAR_INSTITUICOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciar { }
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
	}

	public @interface Conta {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar { }
		
		@PreAuthorize("(hasAuthority('GERENCIAR_CONTAS') or @vipSecurity.podeGerenciarContas(#contaId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("@vipSecurity.podeConsultarContas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('GERENCIAR_CONTAS') or "
				+ "@vipSecurity.usuarioAutenticadoIgual(returnObject.usuario.codigo) or "
				+ "@vipSecurity.podeGerenciarContas(returnObject.codigo)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar { }
		
	}

	public @interface Cartao {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CARTAO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("@vipSecurity.podeGerenciarCartoes(#cartaoId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}

	public @interface Lancamento {
		

		@PreAuthorize("@vipSecurity.podeConsultarLancamentos()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar { }
		
		@PreAuthorize("(hasAuthority('GERENCIAR_LANCAMENTOS') or @vipSecurity.podeGerenciarLancamentos(#lancamentoId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('GERENCIAR_LANCAMENTOS') or "
				+ "@vipSecurity.usuarioAutenticadoIgual(returnObject.usuario.codigo) or "
				+ "@vipSecurity.podeGerenciarLancamentos(returnObject.codigo)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar { }
		
	}

	public @interface Categoria {
		
		@PreAuthorize("@vipSecurity.podeConsultarCategorias()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar { }
		
		@PreAuthorize("(hasAuthority('GERENCIAR_CATEGORIAS') or @vipSecurity.podeGerenciarCategorias(#categoriaId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('GERENCIAR_CATEGORIAS') or "
				+ "@vipSecurity.usuarioAutenticadoIgual(returnObject.usuario.codigo) or "
				+ "@vipSecurity.podeGerenciarCategorias(returnObject.codigo)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar { }
		
	}

}