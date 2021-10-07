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
import br.com.vipautomacao.domain.filter.UsuarioFilter;
import br.com.vipautomacao.domain.model.Usuario;
import br.com.vipautomacao.domain.service.query.UsuarioQueryService;

import br.com.vipautomacao.domain.model.Usuario;

@Repository
public class UsuarioQueryServiceImpl implements UsuarioQueryService {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<Usuario> consultaPorFiltro(UsuarioFilter filtro, String timeOffset, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Usuario.class);
		var root = query.from(Usuario.class);
		var predicates = new ArrayList<Predicate>();
		if(filtro.getCodigo() != null){
			predicates.add(builder.equal(root.get("codigo"), filtro.getCodigo()));


		}
		if(filtro.getNome() != null){
			predicates.add(builder.like(root.get("nome"), 
					filtro.getNome()));


		}
		if(filtro.getEmail() != null){
			predicates.add(builder.like(root.get("email"), 
					filtro.getEmail()));


		}
		if(filtro.getSenha() != null){
			predicates.add(builder.like(root.get("senha"), 
					filtro.getSenha()));


		}
		if(filtro.getDataNascimentoInicio() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("dataNascimento"), 
					filtro.getDataNascimentoInicio()));
		}
		if(filtro.getDataNascimentoFim() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("dataNascimento"), 
					filtro.getDataNascimentoFim()));


		}
		if(filtro.getSexo() != null){
			predicates.add(builder.like(root.get("sexo"), 
					filtro.getSexo()));


		}
		if(filtro.getAcessoInicio() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("acesso"), 
					filtro.getAcessoInicio()));
		}
		if(filtro.getAcessoFim() != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("acesso"), 
					filtro.getAcessoFim()));


		}
		query.where(predicates.toArray(new Predicate[0]));
		
		return manager.createQuery(query).getResultList();
	}
}
