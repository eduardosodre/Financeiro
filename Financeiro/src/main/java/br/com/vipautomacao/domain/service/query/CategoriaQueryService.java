package br.com.vipautomacao.domain.service.query;
import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.CategoriaFilter;
import br.com.vipautomacao.domain.model.Categoria;

public interface CategoriaQueryService {

	List<Categoria> consultaPorFiltro(CategoriaFilter filtro, String timeOffset, Pageable pageable);

	List<Categoria> consultaPorUsuario(Integer usuario, Pageable pageable);
	
}
