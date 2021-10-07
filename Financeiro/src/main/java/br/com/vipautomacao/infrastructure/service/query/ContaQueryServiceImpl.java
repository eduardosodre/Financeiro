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
import br.com.vipautomacao.domain.filter.ContaFilter;
import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.service.query.ContaQueryService;

import br.com.vipautomacao.domain.model.Conta;

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
		if(filtro.getCodigo() != null){
			predicates.add(builder.equal(root.get("codigo"), filtro.getCodigo()));


		}
		if(filtro.getBanco() != null){
			predicates.add(builder.equal(root.get("banco"), filtro.getBanco()));


		}
		if(filtro.getNome() != null){
			predicates.add(builder.like(root.get("nome"), 
					filtro.getNome()));


		}
		if(filtro.getSaldoInicialInicio() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("saldoInicial"), 
					filtro.getSaldoInicialInicio()));
		}
		if(filtro.getSaldoInicialFim() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("saldoInicial"), 
					filtro.getSaldoInicialFim()));


		}
		if(filtro.getDescricao() != null){
			predicates.add(builder.like(root.get("descricao"), 
					filtro.getDescricao()));


		}
		if(filtro.getUsuario() != null){
			predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));


		}
		query.where(predicates.toArray(new Predicate[0]));
		
		return manager.createQuery(query).getResultList();
	}
}
