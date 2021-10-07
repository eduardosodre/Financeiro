package br.com.vipautomacao.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import br.com.vipautomacao.domain.filter.CategoriaFilter;
import br.com.vipautomacao.domain.model.Categoria;
import br.com.vipautomacao.domain.service.query.CategoriaQueryService;

import br.com.vipautomacao.domain.model.Categoria;

@Repository
public class CategoriaQueryServiceImpl implements CategoriaQueryService {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<Categoria> consultaPorFiltro(CategoriaFilter filtro, String timeOffset, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Categoria.class);
		var root = query.from(Categoria.class);
		var predicates = new ArrayList<Predicate>();
		if(filtro.getCodigo() != null){
			predicates.add(builder.equal(root.get("codigo"), filtro.getCodigo()));


		}
		if(filtro.getDescricao() != null){
			predicates.add(builder.like(root.get("descricao"), 
					filtro.getDescricao()));


		}
		if(filtro.getUsuario() != null){
			predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));


		}
		if(filtro.getTipo() != null){
			predicates.add(builder.like(root.get("tipo"), 
					filtro.getTipo()));


		}
		query.where(predicates.toArray(new Predicate[0]));
		
		return manager.createQuery(query).getResultList();
	}
}
