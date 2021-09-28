package br.com.vipautomacao.domain.service.query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.UsuarioFilter;
import br.com.vipautomacao.domain.model.Usuario;

public interface UsuarioQueryService {

	List<Usuario> consultaPorFiltro(UsuarioFilter filtro, String timeOffset, Pageable pageable);
	
}
