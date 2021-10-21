package br.com.vipautomacao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.vipautomacao.domain.model.Conta;
import br.com.vipautomacao.domain.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>, JpaSpecificationExecutor<Lancamento> {

	
	boolean temResponsavel(Integer lancamentoId, Integer usuarioId);
}
