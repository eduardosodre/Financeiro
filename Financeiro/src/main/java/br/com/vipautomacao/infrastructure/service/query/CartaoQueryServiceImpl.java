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
import br.com.vipautomacao.domain.filter.CartaoFilter;
import br.com.vipautomacao.domain.model.Cartao;
import br.com.vipautomacao.domain.service.query.CartaoQueryService;

import br.com.vipautomacao.domain.model.Cartao;

@Repository
public class CartaoQueryServiceImpl implements CartaoQueryService {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<Cartao> consultaPorFiltro(CartaoFilter filtro, String timeOffset, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Cartao.class);
		var root = query.from(Cartao.class);
		var predicates = new ArrayList<Predicate>();
		if(filtro.getCodigo() != null){
			predicates.add(builder.equal(root.get("codigo"), filtro.getCodigo()));


		}
		if(filtro.getInstituicao() != null){
			predicates.add(builder.equal(root.get("instituicao"), filtro.getInstituicao()));


		}
		if(filtro.getFechamento() != null){
			predicates.add(builder.equal(root.get("fechamento"), filtro.getFechamento()));


		}
		if(filtro.getVencimento() != null){
			predicates.add(builder.equal(root.get("vencimento"), filtro.getVencimento()));


		}
		if(filtro.getLimiteInicio() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("limite"), 
					filtro.getLimiteInicio()));
		}
		if(filtro.getLimiteFim() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("limite"), 
					filtro.getLimiteFim()));


		}
		if(filtro.getContaPagamento() != null){
			predicates.add(builder.equal(root.get("contaPagamento"), filtro.getContaPagamento()));


		}
		if(filtro.getUsuario() != null){
			predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));


		}
		query.where(predicates.toArray(new Predicate[0]));
		
		return manager.createQuery(query).getResultList();
	}
}
