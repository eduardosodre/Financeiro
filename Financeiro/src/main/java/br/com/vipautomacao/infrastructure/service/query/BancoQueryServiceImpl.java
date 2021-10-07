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
import br.com.vipautomacao.domain.filter.BancoFilter;
import br.com.vipautomacao.domain.model.Banco;
import br.com.vipautomacao.domain.service.query.BancoQueryService;

import br.com.vipautomacao.domain.model.Banco;

@Repository
public class BancoQueryServiceImpl implements BancoQueryService {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<Banco> consultaPorFiltro(BancoFilter filtro, String timeOffset, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Banco.class);
		var root = query.from(Banco.class);
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
