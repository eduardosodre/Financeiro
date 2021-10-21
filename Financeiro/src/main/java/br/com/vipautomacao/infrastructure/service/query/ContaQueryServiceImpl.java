package br.com.vipautomacao.infrastructure.service.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.vipautomacao.domain.filter.ContaFilter;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.service.query.ContaQueryService;

@Repository
public class ContaQueryServiceImpl implements ContaQueryService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Conta> consultaPorFiltro(ContaFilter filtro, String timeOffset, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Conta.class);
		var root = query.from(Conta.class);
		var predicates = new ArrayList<Predicate>();

		if (filtro.getBanco() != null) {
			predicates.add(builder.equal(root.get("banco"), filtro.getBanco()));

		}
		if (filtro.getNome() != null) {
			predicates.add(builder.like(root.get("nome"), filtro.getNome()));
		}

		if (filtro.getUsuario() != null) {
			predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));

		}
		query.where(predicates.toArray(new Predicate[0]));

		return manager.createQuery(query).getResultList();
	}
	
	
	@Override
	public List<Conta> consultaPorUsuario(Integer usuario, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Conta.class);
		var root = query.from(Conta.class);
		var predicates = new ArrayList<Predicate>();

		if(usuario != null) {
			predicates.add(builder.equal(root.get("usuario"), usuario));
		}
		predicates.add(builder.equal(root.get("cancelado"), false));

		
		query.where(predicates.toArray(new Predicate[0]));

		return manager.createQuery(query).getResultList();
	}
}
