package br.com.vipautomacao.domain.service.query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.CartaoFilter;
import br.com.vipautomacao.domain.model.Cartao;

public interface CartaoQueryService {

	List<Cartao> consultaPorFiltro(CartaoFilter filtro, String timeOffset, Pageable pageable);
	
}
