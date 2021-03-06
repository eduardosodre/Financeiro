package br.com.vipautomacao.domain.service.query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.ContaFilter;
import br.com.vipautomacao.domain.model.Conta;

public interface ContaQueryService {

	List<Conta> consultaPorFiltro(ContaFilter filtro, String timeOffset, Pageable pageable);

	List<Conta> consultaPorUsuario(Integer usuario, Pageable pageable);
	
}
