package br.com.vipautomacao.domain.service.query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.InstituicaoFinanceiraFilter;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;

public interface InstituicaoFinanceiraQueryService {

	List<InstituicaoFinanceira> consultaPorFiltro(InstituicaoFinanceiraFilter filtro, String timeOffset, Pageable pageable);
	
}
