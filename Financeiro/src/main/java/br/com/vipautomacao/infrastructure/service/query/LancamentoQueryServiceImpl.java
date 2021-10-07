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
import br.com.vipautomacao.domain.filter.LancamentoFilter;
import br.com.vipautomacao.domain.model.Lancamento;
import br.com.vipautomacao.domain.service.query.LancamentoQueryService;

import br.com.vipautomacao.domain.model.Lancamento;

@Repository
public class LancamentoQueryServiceImpl implements LancamentoQueryService {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<Lancamento> consultaPorFiltro(LancamentoFilter filtro, String timeOffset, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Lancamento.class);
		var root = query.from(Lancamento.class);
		var predicates = new ArrayList<Predicate>();
		if(filtro.getCodigo() != null){
			predicates.add(builder.equal(root.get("codigo"), filtro.getCodigo()));


		}
		if(filtro.getDescricao() != null){
			predicates.add(builder.like(root.get("descricao"), 
					filtro.getDescricao()));


		}
		if(filtro.getValorInicio() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("valor"), 
					filtro.getValorInicio()));
		}
		if(filtro.getValorFim() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("valor"), 
					filtro.getValorFim()));


		}
		if(filtro.getDataInicio() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("data"), 
					filtro.getDataInicio()));
		}
		if(filtro.getDataFim() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("data"), 
					filtro.getDataFim()));


		}
		if(filtro.getConta() != null){
			predicates.add(builder.equal(root.get("conta"), filtro.getConta()));


		}
		if(filtro.getCategoria() != null){
			predicates.add(builder.equal(root.get("categoria"), filtro.getCategoria()));


		}
		if(filtro.getTipoLancamento() != null){
			predicates.add(builder.like(root.get("tipoLancamento"), 
					filtro.getTipoLancamento()));


		}
		if(filtro.getConta2() != null){
			predicates.add(builder.equal(root.get("conta2"), filtro.getConta2()));


		}
		if(filtro.getObservacao() != null){
			predicates.add(builder.like(root.get("observacao"), 
					filtro.getObservacao()));


		}
		if(filtro.getParcelaFixa() != null){
			if(filtro.getParcelaFixa()){
				predicates.add(builder.isTrue(root.get("parcelaFixa")));
			} else {
				predicates.add(builder.isFalse(root.get("parcelaFixa")));
			}


		}
		if(filtro.getQtdParcela() != null){
			predicates.add(builder.equal(root.get("qtdParcela"), filtro.getQtdParcela()));


		}
		if(filtro.getCodigoPai() != null){
			predicates.add(builder.equal(root.get("codigoPai"), filtro.getCodigoPai()));


		}
		if(filtro.getUsuario() != null){
			predicates.add(builder.equal(root.get("usuario"), filtro.getUsuario()));


		}
		query.where(predicates.toArray(new Predicate[0]));
		
		return manager.createQuery(query).getResultList();
	}
}
