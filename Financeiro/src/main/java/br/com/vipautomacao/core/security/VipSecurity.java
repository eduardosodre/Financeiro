package br.com.vipautomacao.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import br.com.vipautomacao.domain.repository.CartaoRepository;
import br.com.vipautomacao.domain.repository.CategoriaRepository;
import br.com.vipautomacao.domain.repository.ContaRepository;
import br.com.vipautomacao.domain.repository.LancamentoRepository;

@Component
public class VipSecurity {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
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
	
	public Integer getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return ((Long)jwt.getClaim("usuario_id")).intValue();
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
	
	public boolean podeGerenciarCategorias(Integer categoriaId) {
		return isAutenticado() && (hasAuthority("GERENCIAR_CATEGORIAS") || gerenciaCategoria(categoriaId));
	}
	
	public boolean podeConsultarContas() {
		return isAutenticado();
	}
	
	public boolean podeConsultarLancamentos() {
		return isAutenticado();
	}
	
	public boolean podeConsultarCategorias() {
		return isAutenticado();
	}
	
	public boolean podeGerenciarContas(Integer contaId) {
		System.out.println("Contaid: "+contaId);
		return isAutenticado() && (gerenciaConta(contaId));
	}
	
	public boolean podeGerenciarLancamentos(Integer lancamentoId) {
		return isAutenticado() && (gerenciaLancamento(lancamentoId));
	}
	
	public boolean podeGerenciarCartoes(Integer cartaoId) {
		return isAutenticado() && (gerenciaCartao(cartaoId));
	}
	
	public boolean podeGerenciarUsuario(Integer restauranteId) {
		return temEscopoEscrita() && gerenciarUsuario(restauranteId);
	}
	
	
	public boolean gerenciarUsuario(Integer usuarioId) {
		if (usuarioId == null) {
			return false;
		}
		
		return usuarioId.equals(getUsuarioId().intValue());
	}
	
	
	
	
	public boolean gerenciaCartao(Integer cartaoId) {
		if (cartaoId == null) {
			return false;
		}
		
		return cartaoRepository.temResponsavel(cartaoId, getUsuarioId());
	}
	
	public boolean gerenciaCategoria(Integer categoriaId) {
		if (categoriaId == null) {
			return false;
		}
		
		return categoriaRepository.temResponsavel(categoriaId, getUsuarioId());
	}
	
	public boolean gerenciaLancamento(Integer lancamentoId) {
		if (lancamentoId == null) {
			return false;
		}
		
		return lancamentoRepository.temResponsavel(lancamentoId, getUsuarioId());
	}

	public boolean gerenciaConta(Integer contaId) {
		if (contaId == null) {
			System.out.println("Retornando false");
			return false;
		}
		
		System.out.println("Pesquisando "+contaId+" - "+getUsuarioId());
		return contaRepository.temResponsavel(contaId, getUsuarioId());
	}
	
	public boolean usuarioAutenticadoIgual(Integer usuarioId) {
		return getUsuarioId() != null && usuarioId != null
				&& getUsuarioId().equals(usuarioId);
	}
}