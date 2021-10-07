package br.com.vipautomacao.domain.service.query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.BancoFilter;
import br.com.vipautomacao.domain.model.Banco;

public interface BancoQueryService {

	List<Banco> consultaPorFiltro(BancoFilter filtro, String timeOffset, Pageable pageable);
	
}
