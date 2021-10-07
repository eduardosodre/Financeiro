package br.com.vipautomacao.domain.service.query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.LancamentoFilter;
import br.com.vipautomacao.domain.model.Lancamento;

public interface LancamentoQueryService {

	List<Lancamento> consultaPorFiltro(LancamentoFilter filtro, String timeOffset, Pageable pageable);
	
}
