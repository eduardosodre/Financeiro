package br.com.vipautomacao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.vipautomacao.domain.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer>, JpaSpecificationExecutor<Cartao> {

	
	boolean temResponsavel(Integer cartaoId, Integer usuarioId);
}
