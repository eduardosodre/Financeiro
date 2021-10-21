package br.com.vipautomacao.infrastructure.service.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.vipautomacao.domain.filter.CategoriaFilter;
import br.com.vipautomacao.domain.model.Categoria;
import br.com.vipautomacao.domain.service.query.CategoriaQueryService;

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
		if (filtro.getCodigo() != null) {
			predicates.add(builder.equal(root.get("codigo"), filtro.getCodigo()));

		}
		if (filtro.getDescricao() != null) {
			predicates.add(builder.like(root.get("descricao"), "%"+filtro.getDescricao()+"%"));

		}
		if (filtro.getUsuario() != null) {
			//predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));
			
			Predicate pusuario = builder.equal(root.get("usuario"), filtro.getUsuario());
			Predicate pusuario2 = builder.isNull(root.get("usuario"));
			Predicate pusuario3 = builder.or(pusuario, pusuario2);
			predicates.add(pusuario3);

		}
		if (filtro.getTipo() != null) {
			predicates.add(builder.equal(root.get("tipo"), filtro.getTipo()));

		}
		query.where(predicates.toArray(new Predicate[0]));

		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Categoria> consultaPorUsuario(Integer usuario, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Categoria.class);
		var root = query.from(Categoria.class);
		var predicates = new ArrayList<Predicate>();

		if (usuario != null) {
			Predicate pusuario = builder.equal(root.get("usuario"), usuario);
			Predicate pusuario2 = builder.isNull(root.get("usuario"));
			Predicate pusuario3 = builder.or(pusuario, pusuario2);
			predicates.add(pusuario3);
		}
		predicates.add(builder.equal(root.get("cancelado"), false));

		query.where(predicates.toArray(new Predicate[0]));

		return manager.createQuery(query).getResultList();
	}
}
