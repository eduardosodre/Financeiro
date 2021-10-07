package br.com.vipautomacao.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.domain.repository.UsuarioRepository;

@Component
public class VipSecurity {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}
	
	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("usuario_id");
	}

	public boolean temEscopoEscrita() {
		return hasAuthority("SCOPE_WRITE");
	}

	public boolean temEscopoLeitura() {
		return hasAuthority("SCOPE_READ");
	}

	public boolean podeGerenciarBancos() {
		return isAutenticado() && (hasAuthority("GERENCIAR_BANCOS"));
	}
	
	public boolean podeGerenciarInstituicoes() {
		return isAutenticado() && (hasAuthority("GERENCIAR_INSTITUICOES"));
	}
	
	public boolean podeGerenciarCategorias(Integer usuarioId) {
		return isAutenticado() && (hasAuthority("GERENCIAR_CATEGORIAS") || gerenciarUsuario(usuarioId));
		
	}
	
	public boolean podeGerenciarFuncionamentoUsuario(Integer restauranteId) {
		return temEscopoEscrita() && gerenciarUsuario(restauranteId);
	}
	
	
	public boolean gerenciarUsuario(Integer usuarioId) {
		if (usuarioId == null) {
			return false;
		}
		
		return usuarioId.equals(getUsuarioId().intValue());
	}

}