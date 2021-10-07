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
import br.com.vipautomacao.domain.filter.InstituicaoFinanceiraFilter;
import br.com.vipautomacao.domain.model.InstituicaoFinanceira;
import br.com.vipautomacao.domain.service.query.InstituicaoFinanceiraQueryService;

import br.com.vipautomacao.domain.model.InstituicaoFinanceira;

@Repository
public class InstituicaoFinanceiraQueryServiceImpl implements InstituicaoFinanceiraQueryService {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<InstituicaoFinanceira> consultaPorFiltro(InstituicaoFinanceiraFilter filtro, String timeOffset, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(InstituicaoFinanceira.class);
		var root = query.from(InstituicaoFinanceira.class);
		var predicates = new ArrayList<Predicate>();
		if(filtro.getCodigo() != null){
			predicates.add(builder.equal(root.get("codigo"), filtro.getCodigo()));


		}
		if(filtro.getNome() != null){
			predicates.add(builder.like(root.get("nome"), 
					filtro.getNome()));


		}
		if(filtro.getImagem() != null){
			predicates.add(builder.like(root.get("imagem"), 
					filtro.getImagem()));


		}
		query.where(predicates.toArray(new Predicate[0]));
		
		return manager.createQuery(query).getResultList();
	}
}
